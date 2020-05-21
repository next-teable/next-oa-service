package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.MutablePageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectSettlementQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectSettlementRequest;
import cn.com.starest.nextoa.project.domain.rule.ProjectSettlementReference;
import cn.com.starest.nextoa.project.exception.OrderException;
import cn.com.starest.nextoa.project.exception.ProjectNotFoundException;
import cn.com.starest.nextoa.project.exception.ProjectSettlementException;
import cn.com.starest.nextoa.project.exception.ProjectSettlementNotFoundException;
import cn.com.starest.nextoa.project.repository.ContractRepository;
import cn.com.starest.nextoa.project.repository.OrderRepository;
import cn.com.starest.nextoa.project.repository.ProjectCompletionRepository;
import cn.com.starest.nextoa.project.repository.ProjectSettlementRepository;
import cn.com.starest.nextoa.project.service.FinancialReportService;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.ProjectSettlementService;
import cn.com.starest.nextoa.service.AccountService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dz
 */
@Service
public class ProjectSettlementServiceImpl implements ProjectSettlementService {

    @Autowired
    private ModulePermissionService modulePermissionService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProjectSettlementRepository projectSettlementRepository;

    @Autowired
    private ProjectCompletionRepository projectCompletionRepository;

    @Autowired
    private FinancialReportService financialReportService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired(required = false)
    private List<ProjectSettlementReference> projectSettlementReferenceList;

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
        return modulePermissionService.listGrantedActions(Module.PROJECT_SETTLEMENT, byWho)
                                      .toArray(ModuleActions.EMPTY_ACTIONS);
    }

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(ProjectSettlement projectSettlement, User byWho) {
        if (projectSettlement.getStatus() == ProjectSettlement.ProjectSettlementStatus.COMPLETE) {
            return ModuleActions.EMPTY_ACTIONS;
        }
        ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.PROJECT_SETTLEMENT,
                                                                                                 byWho)
                                                                             .toArray(ModuleActions.EMPTY_ACTIONS);
        List<ModuleActions.ModuleAction> result = Lists.newArrayList(grantedActions);
        return result.toArray(ModuleActions.EMPTY_ACTIONS);
    }

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(ProjectCompletion projectCompletion, User byWho) {
        if (projectCompletion.getStatus() == ProjectCompletion.ProjectCompletionStatus.PENDING_SETTLEMENT ||
                projectCompletion.getStatus() == ProjectCompletion.ProjectCompletionStatus.SETTLEMENT_DONE) {
            return ModuleActions.EMPTY_ACTIONS;
        }

        ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.PROJECT_SETTLEMENT,
                                                                                                 byWho)
                                                                             .toArray(ModuleActions.EMPTY_ACTIONS);

        List<ModuleActions.ModuleAction> result = Lists.newArrayList(grantedActions);
        result.removeIf(item -> item == ModuleActions.SETTLE_ACTION);
        result.removeIf(item -> item == ModuleActions.DELETE_ACTION);
        return result.toArray(ModuleActions.EMPTY_ACTIONS);
    }

    @Override
    public ProjectSettlement createProjectSettlement(String id, SaveProjectSettlementRequest request, User byWho) {
        ProjectCompletion projectCompletion = projectCompletionRepository.findById(id);

        if (projectCompletion == null) {
            throw new ProjectSettlementNotFoundException("无效的竣工id");
        }

        if (projectCompletion.getContract() != null &&
                projectSettlementRepository.findFirstByContract(projectCompletion.getContract()) != null) {
            throw new ProjectSettlementException("该合同的待结算数据已存在");
        }

        if (projectCompletion.getOrder() != null &&
                projectSettlementRepository.findFirstByOrder(projectCompletion.getOrder()) != null) {
            throw new ProjectSettlementException("该订单的待结算数据已存在");
        }

        ProjectSettlement projectSettlement = new ProjectSettlement();
        projectSettlement.setProjectCompletion(projectCompletion);

        buildProjectSettlement(request, projectSettlement);
        ProjectSettlement.onCreate(projectSettlement, byWho);
        ProjectSettlement.onModify(projectSettlement, byWho);

        projectCompletion.setStatus(ProjectCompletion.ProjectCompletionStatus.PENDING_SETTLEMENT);
        projectCompletionRepository.save(projectCompletion);

        return projectSettlementRepository.save(projectSettlement);
    }

    @Override
    public ProjectSettlement updateProjectSettlement(String id, SaveProjectSettlementRequest request, User byWho) {
        ProjectSettlement projectSettlement = projectSettlementRepository.findById(id);

        if (projectSettlement == null) {
            throw new ProjectNotFoundException("无效的结算id");
        }

        buildProjectSettlement(request, projectSettlement);
        ProjectSettlement.onModify(projectSettlement, byWho);

        return projectSettlementRepository.save(projectSettlement);
    }

    private void buildProjectSettlement(SaveProjectSettlementRequest request, ProjectSettlement projectSettlement) {
        BeanUtils.copyProperties(request, projectSettlement);

        BigDecimal totalPercent = BigDecimal.ZERO;

        if (projectSettlement.getPercent1() != null) {
            totalPercent = totalPercent.add(projectSettlement.getPercent1());
        }
        if (projectSettlement.getPercent2() != null) {
            totalPercent = totalPercent.add(projectSettlement.getPercent2());
        }
        if (projectSettlement.getPercent3() != null) {
            totalPercent = totalPercent.add(projectSettlement.getPercent3());
        }
        if (projectSettlement.getPercent4() != null) {
            totalPercent = totalPercent.add(projectSettlement.getPercent4());
        }
        if (projectSettlement.getPercent5() != null) {
            totalPercent = totalPercent.add(projectSettlement.getPercent5());
        }
        if (projectSettlement.getMarketPercent1() != null) {
            totalPercent = totalPercent.add(projectSettlement.getMarketPercent1());
        }
        if (projectSettlement.getMarketPercent2() != null) {
            totalPercent = totalPercent.add(projectSettlement.getMarketPercent2());
        }
        if (projectSettlement.getMarketPercent3() != null) {
            totalPercent = totalPercent.add(projectSettlement.getMarketPercent3());
        }

        if (totalPercent.compareTo(new BigDecimal(100)) > 0) {
            throw new ProjectSettlementException("提成比例累计不能超过100%");
        }

        int userCount = 0;
        Set<String> userIdSet = new HashSet<>();

        if (!StringUtils.isEmpty(request.getUser1Id())) {
            userCount++;
            userIdSet.add(request.getUser1Id());
        }
        if (!StringUtils.isEmpty(request.getUser2Id())) {
            userCount++;
            userIdSet.add(request.getUser2Id());
        }
        if (!StringUtils.isEmpty(request.getUser3Id())) {
            userCount++;
            userIdSet.add(request.getUser3Id());
        }
        if (!StringUtils.isEmpty(request.getUser4Id())) {
            userCount++;
            userIdSet.add(request.getUser4Id());
        }
        if (!StringUtils.isEmpty(request.getUser5Id())) {
            userCount++;
            userIdSet.add(request.getUser5Id());
        }

        if (userIdSet.size() < userCount) {
            throw new ProjectSettlementException("请勿设置重复的提成用户");
        }

        if (!StringUtils.isEmpty(request.getUser1Id())) {
            User user = accountService.findById(request.getUser1Id());
            projectSettlement.setUser1(user);
        }
        else {
            projectSettlement.setUser1(null);
        }

        if (!StringUtils.isEmpty(request.getUser2Id())) {
            User user = accountService.findById(request.getUser2Id());
            projectSettlement.setUser2(user);
        }
        else {
            projectSettlement.setUser2(null);
        }

        if (!StringUtils.isEmpty(request.getUser3Id())) {
            User user = accountService.findById(request.getUser3Id());
            projectSettlement.setUser3(user);
        }
        else {
            projectSettlement.setUser3(null);
        }

        if (!StringUtils.isEmpty(request.getUser4Id())) {
            User user = accountService.findById(request.getUser4Id());
            projectSettlement.setUser4(user);
        }
        else {
            projectSettlement.setUser4(null);
        }

        if (!StringUtils.isEmpty(request.getUser5Id())) {
            User user = accountService.findById(request.getUser5Id());
            projectSettlement.setUser5(user);
        }
        else {
            projectSettlement.setUser5(null);
        }
    }

    @Override
    public ProjectSettlement findProjectSettlementById(String id, User byWho) {
        return projectSettlementRepository.findById(id);
    }

    @Override
    public void deleteProjectSettlementById(String id, User user) {
        ProjectSettlement projectSettlement = projectSettlementRepository.findById(id);

        if (projectSettlement == null) {
            return;
        }

        if (projectSettlement.getStatus() == ProjectSettlement.ProjectSettlementStatus.COMPLETE) {
            throw new ProjectSettlementException("不能删除已结算的数据");
        }

        if (projectSettlementReferenceList != null) {
            projectSettlementReferenceList.forEach(ref -> {
                if (ref.hasReference(projectSettlement)) {
                    throw new OrderException("该数据已被其他业务引用,不能删除");
                }
            });
        }

        ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregation(
                projectSettlement);
        if (projectSettlementAggregation.getWithdrawAmount1() != null &&
                projectSettlementAggregation.getWithdrawAmount1().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("不能删除已提成的数据");
        }
        if (projectSettlementAggregation.getWithdrawAmount2() != null &&
                projectSettlementAggregation.getWithdrawAmount2().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("不能删除已提成的数据");
        }
        if (projectSettlementAggregation.getWithdrawAmount3() != null &&
                projectSettlementAggregation.getWithdrawAmount3().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("不能删除已提成的数据");
        }
        if (projectSettlementAggregation.getWithdrawAmount4() != null &&
                projectSettlementAggregation.getWithdrawAmount4().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("不能删除已提成的数据");
        }
        if (projectSettlementAggregation.getWithdrawAmount5() != null &&
                projectSettlementAggregation.getWithdrawAmount5().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("不能删除已提成的数据");
        }

        projectSettlementRepository.delete(projectSettlement);

        ProjectCompletion projectCompletion = projectSettlement.getProjectCompletion();
        projectCompletion.setStatus(ProjectCompletion.ProjectCompletionStatus.COMPLETE);
        projectCompletionRepository.save(projectCompletion);
    }

    @Override
    public void doProjectSettlement(String id, User byWho) {
        ProjectSettlement projectSettlement = projectSettlementRepository.findById(id);

        if (projectSettlement == null) {
            throw new ProjectSettlementException("无效的id");
        }

        if (projectSettlement.getStatus() == ProjectSettlement.ProjectSettlementStatus.COMPLETE) {
            throw new ProjectSettlementException("请勿重复结算");
        }

        ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregation(
                projectSettlement);
        if (projectSettlementAggregation.getLeftAmount1() != null &&
                projectSettlementAggregation.getLeftAmount1().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("主管1还有剩余未提成金额");
        }
        if (projectSettlementAggregation.getLeftAmount2() != null &&
                projectSettlementAggregation.getLeftAmount2().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("主管2还有剩余未提成金额");
        }
        if (projectSettlementAggregation.getLeftAmount3() != null &&
                projectSettlementAggregation.getLeftAmount3().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("主管3还有剩余未提成金额");
        }
        if (projectSettlementAggregation.getLeftAmount4() != null &&
                projectSettlementAggregation.getLeftAmount4().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("市场部还有剩余未提成金额");
        }
        if (projectSettlementAggregation.getLeftAmount5() != null &&
                projectSettlementAggregation.getLeftAmount5().compareTo(BigDecimal.ZERO) > 0) {
            throw new ProjectSettlementException("项目经理还有剩余未提成金额");
        }

        projectSettlement.setStatus(ProjectSettlement.ProjectSettlementStatus.COMPLETE);
        projectSettlementRepository.save(projectSettlement);

        if (projectSettlement.getOrder() != null) {
            Order order = projectSettlement.getOrder();
            order.setSettled(true);
            order.setSettledAt(new Date());
            order.setSettledBy(byWho);

            orderRepository.save(order);
        }

        if (projectSettlement.getContract() != null) {
            Contract contract = projectSettlement.getContract();
            contract.setSettled(true);
            contract.setSettledAt(new Date());
            contract.setSettledBy(byWho);

            contractRepository.save(contract);
        }

        ProjectCompletion projectCompletion = projectSettlement.getProjectCompletion();
        projectCompletion.setStatus(ProjectCompletion.ProjectCompletionStatus.COMPLETE);
        projectCompletionRepository.save(projectCompletion);
    }

    @Override
    public Page<ProjectSettlement> listProjectSettlements(ProjectSettlementQueryRequest request) {
        return projectSettlementRepository.queryPage(request);
    }

    @Override
    public Page<ProjectSettlement> listProjectSettlements(ProjectSettlementQueryRequest request, User byWho) {
        return projectSettlementRepository.queryPage(request, byWho);
    }

    /**
     * @param request
     * @param byWho
     * @return
     */
    @Override
    public ProjectSettlementAggregation calculateProjectSettlementAggregation(ProjectSettlementQueryRequest request,
                                                                              User byWho) {
        ProjectSettlementAggregation result = new ProjectSettlementAggregation();

        Page<ProjectSettlement> projectSettlementPage = projectSettlementRepository.queryPage(request, byWho);

        projectSettlementPage.getContent().stream().forEach(item -> {
            ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregationByOneUser(
                    item,
                    byWho);

            ProjectSettlementAggregation.add(result, projectSettlementAggregation);
        });

        for (; projectSettlementPage.hasNext(); ) {
            if (request instanceof MutablePageQueryRequest) {
                ((MutablePageQueryRequest) request).setStart(request.getStart() + 1);
            }
            else {
                break;
            }

            projectSettlementPage = projectSettlementRepository.queryPage(request, byWho);

            projectSettlementPage.getContent().stream().forEach(item -> {
                ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregationByOneUser(
                        item,
                        byWho);

                ProjectSettlementAggregation.add(result, projectSettlementAggregation);
            });
        }

        return result;
    }
}
