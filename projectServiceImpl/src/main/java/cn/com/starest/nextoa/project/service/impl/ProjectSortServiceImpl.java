package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectFinancialAggregation;
import cn.com.starest.nextoa.project.domain.model.ProjectStatus;
import cn.com.starest.nextoa.project.exception.ProjectException;
import cn.com.starest.nextoa.project.repository.ContractRepository;
import cn.com.starest.nextoa.project.repository.OrderRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import cn.com.starest.nextoa.project.service.FinancialReportService;
import cn.com.starest.nextoa.project.service.ProjectSortService;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author dz
 */
@Service
public class ProjectSortServiceImpl implements ProjectSortService, EventListener<Long>, InitializingBean {

	private static final Log logger = LogFactory.getLog(ProjectSortServiceImpl.class);

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private FinancialReportService financialReportService;

	private long latestSortTimestamp;

	@Override
	public void sortProjects() {
		if (System.currentTimeMillis() - this.latestSortTimestamp <= 5000) {
			throw new ProjectException("重新计算排序每5分钟允许操作一次, 离上一次请求（本人或者其他用户）还不到5秒.");
		}

		this.latestSortTimestamp = System.currentTimeMillis();

		Edms.getEdm("project").dispatch("resort", System.currentTimeMillis());
	}

	@Override
	public void clearProjectSorts() {
		//清空所有排序,准备重新计算
		logger.debug("clear sort start");

		projectRepository.findAll().forEach(project -> {
			projectRepository.clearSort(project);
		});

		logger.debug("clear sort done");
	}

	class ProjectSorter {
		//convert for calculation
		Map<String,ProjectContractAmount> projectContractAmountMap = new HashMap<>();
		List<ProjectContractAmount> projectContractAmountList = new ArrayList<>();
		List<ProjectContractAmount> projectZeroOrderAmountList = new ArrayList<>();

		public ProjectSorter(Map<String,ProjectContractAmount> projectContractAmountMap,
							 List<ProjectContractAmount> projectContractAmountList,
							 List<ProjectContractAmount> projectZeroOrderAmountList) {
			this.projectContractAmountMap = projectContractAmountMap;
			this.projectContractAmountList = projectContractAmountList;
			this.projectZeroOrderAmountList = projectZeroOrderAmountList;
		}

		ProjectContractAmount getProjectContractAmount() {
			for (ProjectContractAmount projectContractAmount : projectContractAmountList) {
				//remove and return the first one
				projectContractAmountList.remove(projectContractAmount);
				return projectContractAmount;
			}

			return null;
		}

		void process() {
			int projectSortNo = 0;
			// 最前面排进行中的，首先根据合同金额选择优先排的地市（合同金额最大的项目归属地市排第一）
			for (ProjectContractAmount projectContractAmount = getProjectContractAmount(); projectContractAmount !=
																						   null; projectContractAmount = getProjectContractAmount()) {

				logger.debug(projectContractAmount.getProject().getName() +
							 "contract amount" +
							 projectContractAmount.getAmount());

				Project matchedProject = projectContractAmount.getProject();
				projectRepository.sortProject(matchedProject, String.format("A%08d", projectSortNo++));

				List<Project> projectList = projectRepository.findListByCity(matchedProject.getCity());
				List<ProjectSort> projectSortList = new ArrayList<>();

				for (Project project : projectList) {
					if (matchedProject.getId().equals(project.getId())) {
						//skip the processed project
						continue;
					}

					if (project.getStatus() == ProjectStatus.DRAFT) {
						//skip the draft
						continue;
					}

					BigDecimal contractAmount = contractRepository.calculateTotalAmount(project);
					BigDecimal orderAmount = orderRepository.calculateTotalAmount(project);

					projectSortList.add(new ProjectSort(project, contractAmount, orderAmount));

					ProjectContractAmount matchedProjectContractAmount = projectContractAmountMap.get(project.getId());
					if (matchedProjectContractAmount != null) {
						projectContractAmountList.remove(matchedProjectContractAmount);
						projectZeroOrderAmountList.remove(matchedProjectContractAmount);
					}
				}

				// 该地市的全部项目订单金额不为0的根据合同金额由大到小排前面
				// 订单金额为0的根据合同金额由大到小排后面
				Collections.sort(projectSortList, (left, right) -> {
					if (right.getOrderAmount().compareTo(BigDecimal.ZERO) > 0 &&
						left.getOrderAmount().compareTo(BigDecimal.ZERO) <= 0) {
						return 1;
					}

					if (right.getOrderAmount().compareTo(BigDecimal.ZERO) <= 0 &&
						left.getOrderAmount().compareTo(BigDecimal.ZERO) > 0) {
						return -1;
					}

					return right.getContractAmount().compareTo(left.getContractAmount());
				});

				for (ProjectSort projectSort : projectSortList) {
					projectRepository.sortProject(projectSort.getProject(), String.format("A%08d", projectSortNo++));
				}
			}

			//handle the left project
			for (ProjectContractAmount projectContractAmount : projectZeroOrderAmountList) {
				projectRepository.sortProject(projectContractAmount.getProject(),
											  String.format("A%08d", projectSortNo++));
			}
		}
	}

	public void onEvent(Long currentTimeStamp) {
		//清空所有排序,准备重新计算
		logger.debug("clear sort start");

		projectRepository.findAll().forEach(project -> {
			projectRepository.clearSort(project);
		});

		logger.debug("clear sort done");

		// 结项的项目排到最后（根据结项时间顺序排）；
		// 编码规则: C0000000x
		logger.debug("resort closed project start");

		int i = 0;
		for (Project project : projectRepository.findListByStatusOrderByCloseAtDesc(ProjectStatus.CLOSED)) {
			projectRepository.sortProject(project, String.format("C%08d", i++));
		}
		logger.debug("resort closed project done");

		logger.debug("prepare data start");
		//aggregation in memory
		List<ProjectContractAmount> projectAmountList = new ArrayList<>();

		projectRepository.findAll().forEach(project -> {
			try {
				ProjectFinancialAggregation projectFinancialAggregation = financialReportService.calculateProjectFinancialReport(
						project,
						null);

				project.setCurrentCollectProfitAmount(projectFinancialAggregation.getCollectProfitAmount());
				project.setCurrentCollectProfitRate(projectFinancialAggregation.getCollectProfitRate());

				projectRepository.save(project);
			}
			catch (Throwable e) {
				logger.error(e);
			}

			BigDecimal amount = contractRepository.calculateTotalAmount(project);
			projectAmountList.add(new ProjectContractAmount(project, amount));
		});

		//SORT DESC
		Collections.sort(projectAmountList, (from, to) -> to.getAmount().compareTo(from.getAmount()));

		//convert for calculation
		Map<String,ProjectContractAmount> projectContractAmountMap = new HashMap<>();
		List<ProjectContractAmount> projectContractAmountList = new ArrayList<>();
		List<ProjectContractAmount> projectZeroOrderAmountList = new ArrayList<>();

		//convert to map and convert to ProjectContractAmount
		for (ProjectContractAmount projectContractAmount : projectAmountList) {
			Project project = projectContractAmount.getProject();

			BigDecimal orderAmount = orderRepository.calculateTotalAmount(project);
			if (orderAmount.compareTo(BigDecimal.ZERO) <= 0) {
				projectZeroOrderAmountList.add(projectContractAmount);
			}
			else {
				projectContractAmountList.add(projectContractAmount);
			}
			projectContractAmountMap.put(project.getId(), projectContractAmount);
		}

		logger.debug("prepare data done");

		//sort project
		new ProjectSorter(projectContractAmountMap, projectContractAmountList, projectZeroOrderAmountList).process();

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm("project").register("resort", this);
	}

}
