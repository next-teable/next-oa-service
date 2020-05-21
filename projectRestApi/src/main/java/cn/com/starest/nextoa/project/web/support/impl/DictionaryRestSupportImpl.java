package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.service.DictionaryService;
import cn.com.starest.nextoa.project.web.dto.DictionarySummary;
import cn.com.starest.nextoa.project.web.dto.SaveDictionaryParameter;
import cn.com.starest.nextoa.project.web.support.DictionaryRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class DictionaryRestSupportImpl implements DictionaryRestSupport {

	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public List<DictionarySummary> listProjectTypes() {
		return dictionaryService.listProjectTypes(new PageQueryParameter(0, 100))
								.getContent()
								.stream()
								.map(DictionarySummary::from)
								.collect(Collectors.toList());
	}

	@Override
	public DictionarySummary createProjectType(SaveDictionaryParameter request, User byWho) {
		return DictionarySummary.from(dictionaryService.createProjectType(request, byWho));
	}

	@Override
	public DictionarySummary updateProjectType(String id, SaveDictionaryParameter request, User byWho) {
		return DictionarySummary.from(dictionaryService.updateProjectType(id, request, byWho));
	}

	@Override
	public DictionarySummary findProjectTypeById(String id) {
		return DictionarySummary.from(dictionaryService.findProjectTypeById(id));
	}

	@Override
	public List<DictionarySummary> listProjectDeliveryTypes() {
		return dictionaryService.listProjectDeliveryTypes(new PageQueryParameter(0, 100))
								.getContent()
								.stream()
								.map(DictionarySummary::from)
								.collect(Collectors.toList());
	}

	@Override
	public DictionarySummary createProjectDeliveryType(SaveDictionaryParameter request, User byWho) {
		return DictionarySummary.from(dictionaryService.createProjectDeliveryType(request, byWho));
	}

	@Override
	public DictionarySummary updateProjectDeliveryType(String id, SaveDictionaryParameter request, User byWho) {
		return DictionarySummary.from(dictionaryService.updateProjectDeliveryType(id, request, byWho));
	}

	@Override
	public DictionarySummary findProjectDeliveryTypeById(String id) {
		return DictionarySummary.from(dictionaryService.findProjectDeliveryTypeById(id));
	}

	@Override
	public List<DictionarySummary> listContractTypes() {
		return dictionaryService.listContractTypes(new PageQueryParameter(0, 100))
								.getContent()
								.stream()
								.map(DictionarySummary::from)
								.collect(Collectors.toList());
	}

	@Override
	public DictionarySummary createContractType(SaveDictionaryParameter request, User byWho) {
		return DictionarySummary.from(dictionaryService.createContractType(request, byWho));
	}

	@Override
	public DictionarySummary updateContractType(String id, SaveDictionaryParameter request, User byWho) {
		return DictionarySummary.from(dictionaryService.updateContractType(id, request, byWho));
	}

	@Override
	public DictionarySummary findContractTypeById(String id) {
		return DictionarySummary.from(dictionaryService.findContractTypeById(id));
	}

	@Override
	public List<DictionarySummary> listDepositTypes() {
		return dictionaryService.listDepositTypes(new PageQueryParameter(0, 100))
								.getContent()
								.stream()
								.map(DictionarySummary::from)
								.collect(Collectors.toList());
	}

	@Override
	public DictionarySummary createDepositType(SaveDictionaryParameter request, User byWho) {
		return DictionarySummary.from(dictionaryService.createDepositType(request, byWho));
	}

	@Override
	public DictionarySummary updateDepositType(String id, SaveDictionaryParameter request, User byWho) {
		return DictionarySummary.from(dictionaryService.updateDepositType(id, request, byWho));
	}

	@Override
	public DictionarySummary findDepositTypeById(String id) {
		return DictionarySummary.from(dictionaryService.findDepositTypeById(id));
	}

}
