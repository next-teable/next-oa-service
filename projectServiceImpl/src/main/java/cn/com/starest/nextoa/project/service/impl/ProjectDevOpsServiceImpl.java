package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.NamedPageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.BaseDataService;
import cn.com.starest.nextoa.project.service.CompanyCapitalService;
import cn.com.starest.nextoa.project.service.ProjectDevOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author dz
 */
@Service
public class ProjectDevOpsServiceImpl implements ProjectDevOpsService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private LendingRepository lendingRepository;

	@Autowired
	private SalaryRepository salaryRepository;

	@Autowired
	private SalaryAggregationRepository salaryAggregationRepository;

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Autowired
	private BaseDataService baseDataService;

	@Autowired
	private CompanyCapitalService companyCapitalService;

	@Override
	public void fixedPaymentTimeOfReimburse(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		paymentRepository.findPageBySource(PaymentSource.REIMBURSE, new PageRequest(0, 10000))
						 .getContent()
						 .stream()
						 .forEach(payment -> {
							 if (payment.getPayAt() == null) {
								 payment.setPayAt(payment.getCreatedAt());
								 paymentRepository.save(payment);
							 }
						 });

	}

	@Override
	public void fixedLendingObject(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		lendingRepository.findAll(new PageRequest(0, 10000)).getContent().stream().forEach(lending -> {

			if (lending.getLendingObject() == LendingObject.COMPANY) {
				lending.setLendingBy(null);
				lendingRepository.save(lending);
			}
			else if (lending.getLendingObject() == LendingObject.PERSONAL) {
				lending.setLendingTo(null);
				lendingRepository.save(lending);
			}
		});

	}

	@Override
	public void fixedDetachedSalary(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		salaryRepository.findAll(new PageRequest(0, 10000)).getContent().stream().forEach(salary -> {
			if (salary.getCompany() == null) {
				salaryRepository.delete(salary);
			}
		});
	}

	@Override
	public void fixSalaryAggregationByYear(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		salaryAggregationRepository.findAll(new PageRequest(0, 1000)).getContent().stream().forEach(salaryAggr -> {
			if (salaryAggr.getMonth() == -1) {
				return;
			}

			Company company = salaryAggr.getCompany();
			int year = salaryAggr.getYear();

			SalaryAggregation salaryAggregation = salaryAggregationRepository.findFirstByCompanyAndYearAndMonth(company,
																												year,
																												-1);

			if (salaryAggregation == null) {
				salaryAggregation = createIfPossible(company, year, -1);
			}

			salaryAggregation.setTotalPay(salaryRepository.calculateTotalPay(company, year));
			salaryAggregation.setModifiedAt(new Date());
			salaryAggregationRepository.save(salaryAggregation);
		});
	}

	@Override
	public void fixPendingPaymentDate(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		pendingPaymentRepository.findAll(new PageRequest(0, 1000)).getContent().stream().forEach(pendingPayment -> {
			switch (pendingPayment.getReceivedPaymentSource()) {
				case PROJECT:
					ProjectReceivedPayment projectReceivedPayment = pendingPayment.getProjectReceivedPayment();
					if (projectReceivedPayment != null) {
						pendingPayment.setPendingAt(projectReceivedPayment.getReceivedAt());
						pendingPaymentRepository.save(pendingPayment);
					}

					break;
				case COMPANY:
					CompanyReceivedPayment companyReceivedPayment = pendingPayment.getCompanyReceivedPayment();
					if (companyReceivedPayment != null) {
						pendingPayment.setPendingAt(companyReceivedPayment.getReceivedAt());
						pendingPaymentRepository.save(pendingPayment);
					}

					break;
			}
		});
	}

	@Override
	public void calculateCompanyInitialCapital(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		baseDataService.listCompanies(NamedPageQueryRequest.DUMMY)
					   .getContent()
					   .stream()
					   .forEach(company -> companyCapitalService.autoCalculateInitialCapital(company));
	}

	private synchronized SalaryAggregation createIfPossible(Company company, int year, int month) {
		//re-query for concurrent request
		SalaryAggregation salaryAggregation = salaryAggregationRepository.findFirstByCompanyAndYearAndMonth(company,
																											year,
																											month);
		if (salaryAggregation == null) {
			salaryAggregation = new SalaryAggregation();
			salaryAggregation.setCompany(company);
			salaryAggregation.setYear(year);
			salaryAggregation.setMonth(month);
			salaryAggregation = salaryAggregationRepository.save(salaryAggregation);
		}
		return salaryAggregation;
	}


}
