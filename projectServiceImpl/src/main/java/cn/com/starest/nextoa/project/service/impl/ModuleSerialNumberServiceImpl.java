package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.domain.model.ModuleSerialNumber;
import cn.com.starest.nextoa.project.repository.ModuleSerialNumberRepository;
import cn.com.starest.nextoa.project.service.ModuleSerialNumberService;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author dz
 */
@Service
public class ModuleSerialNumberServiceImpl implements ModuleSerialNumberService {

	@Autowired
	private ModuleSerialNumberRepository moduleSerialNumberRepository;

	@Override
	public String generate(Module moduleFeature) {
		switch (moduleFeature) {
			case PROJECT_RECEIVED_PAYMENT: {
				return generateProjectReceivedPaymentCode();
			}
			case COMPANY_RECEIVED_PAYMENT: {
				return generateCompanyReceivedPaymentCode();
			}
			case PAYMENT: {
				return generatePaymentCode();
			}
		}
		return null;
	}

	private String generateCompanyReceivedPaymentCode() {
		return generateCode(Module.COMPANY_RECEIVED_PAYMENT, "GSHK");

	}


	private synchronized String generateProjectReceivedPaymentCode() {
		return generateCode(Module.PROJECT_RECEIVED_PAYMENT, "XMHK");
	}

	private synchronized String generatePaymentCode() {
		return generateCode(Module.PAYMENT, "FK");
	}

	private String generateCode(Module moduleFeature, String prefix) {
		ModuleSerialNumber moduleSerialNumber = moduleSerialNumberRepository.findById(moduleFeature.name());
		if (moduleSerialNumber == null) {
			moduleSerialNumber = new ModuleSerialNumber();
			moduleSerialNumber.setId(moduleFeature.name());
			moduleSerialNumber = moduleSerialNumberRepository.save(moduleSerialNumber);
		}

		String today = DateUtils.formatDate(new Date(), "yyyyMMdd");
		if (!today.equals(moduleSerialNumber.getDate())) {
			moduleSerialNumber.setDate(today);
			moduleSerialNumber.setNumber(0);
			moduleSerialNumber = moduleSerialNumberRepository.save(moduleSerialNumber);
		}

		int number = moduleSerialNumber.getNumber();
		number += 1;
		moduleSerialNumber.setNumber(number);
		moduleSerialNumberRepository.save(moduleSerialNumber);

		return prefix + today + String.format("%04d", number);
	}


}
