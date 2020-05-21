package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.IssueInvoiceQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveIssueInvoiceRequest;
import cn.com.starest.nextoa.project.domain.rule.IssueInvoiceObserver;
import cn.com.starest.nextoa.project.domain.rule.IssueInvoiceReference;
import cn.com.starest.nextoa.project.exception.*;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.IssueInvoiceService;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dz
 */
@Service
public class IssueInvoiceServiceImpl implements IssueInvoiceService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FirstPartyRepository firstPartyRepository;

    @Autowired(required = false)
    private List<IssueInvoiceReference> issueInvoiceReferenceList;

    @Autowired(required = false)
    private List<IssueInvoiceObserver> issueInvoiceObserverList;

    @Autowired
    private ModulePermissionService modulePermissionService;

    @Autowired
    private IssueInvoiceRepository issueInvoiceRepository;

    @Autowired
    private ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
        return modulePermissionService.listGrantedActions(Module.ISSUE_INVOICE, byWho)
                .toArray(ModuleActions.EMPTY_ACTIONS);
    }

    @Override
    public ModuleActions.ModuleAction[] resolveGrantedActions(IssueInvoice issueInvoice, User byWho) {
        ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.ISSUE_INVOICE,
                byWho)
                .toArray(ModuleActions.EMPTY_ACTIONS);

        return grantedActions;
    }

    @Override
    public IssueInvoice createIssueInvoice(SaveIssueInvoiceRequest request, User byWho) {
        IssueInvoice issueInvoice = new IssueInvoice();

        BeanUtils.copyProperties(request, issueInvoice);
        handleIssueInvoiceReference(request, issueInvoice);
        IssueInvoice.onCreate(issueInvoice, byWho);

        return issueInvoiceRepository.save(issueInvoice);
    }

    @Override
    public IssueInvoice updateIssueInvoice(String id, SaveIssueInvoiceRequest request, User byWho) {
        IssueInvoice issueInvoice = issueInvoiceRepository.findById(id);
        if (issueInvoice == null) {
            throw new IssueInvoiceNotFoundException("没有找到对应的开票.");
        }

        BeanUtils.copyProperties(request, issueInvoice);
        handleIssueInvoiceReference(request, issueInvoice);
        IssueInvoice.onModify(issueInvoice, byWho);

        if (issueInvoiceObserverList != null) {
            issueInvoiceObserverList.forEach(observer -> {
                try {
                    observer.onChange(issueInvoice);
                } catch (Throwable e) {
                }
            });
        }


        return issueInvoiceRepository.save(issueInvoice);
    }

    @Override
    public void deleteIssueInvoiceById(String id, User byWho) {
        IssueInvoice issueInvoice = issueInvoiceRepository.findById(id);
        if (issueInvoice == null) {
            throw new IssueInvoiceNotFoundException("没有找到对应的开票.");
        }

        if (issueInvoiceReferenceList != null) {
            issueInvoiceReferenceList.forEach(ref -> {
                if (ref.hasReference(issueInvoice)) {
                    throw new IssueInvoiceException("该开票已被其他业务数据引用,不能删除.");
                }
            });
        }

        issueInvoiceRepository.delete(issueInvoice);
    }

    @Override
    public List<IssueInvoice> listIssueInvoicesByProject(String id, User user) {
        Project project = projectRepository.findById(id);
        if (project == null) {
            throw new ProjectNotFoundException("无效的项目id");
        }
        return issueInvoiceRepository.findListByProjectOrderByCreatedAtDesc(project);
    }

    @Override
    public BigDecimal calculateReceivedAmount(IssueInvoice issueInvoice) {
        return projectReceivedPaymentRepository.calculateTotalAmount(issueInvoice);
    }

    @Override
    public IssueInvoice findIssueInvoiceById(String id, User byWho) {
        return issueInvoiceRepository.findById(id);
    }

    @Override
    public Page<IssueInvoice> listIssueInvoices(IssueInvoiceQueryRequest request, User byWho) {
        return issueInvoiceRepository.queryPage(request);
    }

    private void handleIssueInvoiceReference(SaveIssueInvoiceRequest request, IssueInvoice issueInvoice) {
        Company company = companyRepository.findById(request.getCompanyId());
        if (company == null) {
            throw new EntityNotFoundException("无效的公司id");
        }

        Project project = projectRepository.findById(request.getProjectId());
        if (project == null) {
            throw new EntityNotFoundException("无效的项目id");
        }

        Contract contract = contractRepository.findById(request.getContractId());
        if (contract == null) {
            throw new EntityNotFoundException("无效的合同id");
        }

        if (issueInvoice.getContract() != null &&
                issueInvoice.getContract().isSettled() &&
                !issueInvoice.getContract().getId().equals(request.getContractId())) {
            throw new ContractException("开票对应的合同已经结算,不能修改为其他合同");
        } else if (contract.isSettled()) {
            throw new ContractException("不能选择已经结算的合同");
        }

        FirstParty firstParty = firstPartyRepository.findById(request.getFirstPartyId());
        if (firstParty == null) {
            throw new EntityNotFoundException("无效的甲方id");
        }

        issueInvoice.setProject(project);
        issueInvoice.setContract(contract);
        issueInvoice.setCompany(company);
        issueInvoice.setFirstParty(firstParty);

        if (issueInvoice.getOrder() != null &&
                issueInvoice.getOrder().isSettled() &&
                !issueInvoice.getOrder().getId().equals(request.getOrderId())) {
            throw new OrderException("开票对应的订单已经结算,不能修改为其他订单");
        }

        if (!StringUtils.isEmpty(request.getOrderId())) {
            Order order = orderRepository.findById(request.getOrderId());
            if (order == null) {
                throw new EntityNotFoundException("无效的订单id");
            }

            if (issueInvoice.getOrder() != null &&
                    issueInvoice.getOrder().isSettled() &&
                    !issueInvoice.getOrder().getId().equals(request.getOrderId())) {
                throw new OrderException("开票对应的订单已经结算,不能修改为其他订单");
            } else if (order.isSettled()) {
                throw new OrderException("不能选择已经结算的订单");
            }

            issueInvoice.setOrder(order);
            issueInvoice.setOrderYear(order.getYear());
        } else {
//			issueInvoice.setOrder(null);
            //必须选择主订单 20180102
            throw new IllegalArgumentException("请选择主订单");
        }

    }

}
