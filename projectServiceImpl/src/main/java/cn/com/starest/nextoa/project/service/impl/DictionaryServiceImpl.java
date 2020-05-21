package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.ContractType;
import cn.com.starest.nextoa.project.domain.model.DepositType;
import cn.com.starest.nextoa.project.domain.model.ProjectDeliveryType;
import cn.com.starest.nextoa.project.domain.model.ProjectType;
import cn.com.starest.nextoa.project.domain.request.SaveDictionaryRequest;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.repository.ContractTypeRepository;
import cn.com.starest.nextoa.project.repository.DepositTypeRepository;
import cn.com.starest.nextoa.project.repository.ProjectDeliveryTypeRepository;
import cn.com.starest.nextoa.project.repository.ProjectTypeRepository;
import cn.com.starest.nextoa.project.service.DictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.util.Date;

/**
 * @author dz
 */
@Service
public class DictionaryServiceImpl implements DictionaryService, InitializingBean {

	@Autowired
	private ContractTypeRepository contractTypeRepository;

	@Autowired
	private ProjectDeliveryTypeRepository projectDeliveryTypeRepository;

	@Autowired
	private ProjectTypeRepository projectTypeRepository;

	@Autowired
	private DepositTypeRepository depositTypeRepository;

	@Override
	public ProjectType createProjectType(SaveDictionaryRequest request, User byWho) {
		if (projectTypeRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的项目类型");
		}

		ProjectType result = new ProjectType();
		BeanUtils.copyProperties(request, result);
		result.setId(null);
		result.setModifiedBy(byWho);
		result.setModifiedAt(new Date());

		return projectTypeRepository.save(result);
	}

	@Override
	public ProjectType updateProjectType(String id, SaveDictionaryRequest request, User byWho) {
		ProjectType projectType = projectTypeRepository.findById(id);
		if (projectType == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		ProjectType matchedProjectType = projectTypeRepository.findFirstByName(request.getName());
		if (matchedProjectType != null && !matchedProjectType.getId().equals(id)) {
			throw new ValidationException("重复的项目类型");
		}

		projectType.setName(request.getName());
		projectType.setDescription(request.getDescription());
		projectType.setModifiedBy(byWho);
		projectType.setModifiedAt(new Date());

		return projectTypeRepository.save(projectType);
	}

	@Override
	public ProjectType findProjectTypeById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return projectTypeRepository.findById(id);
	}

	@Override
	public Page<ProjectType> listProjectTypes(PageQueryRequest request) {
		return projectTypeRepository.findAll(new PageRequest(request.getStart(), request.getLimit()));
	}

	@Override
	public ProjectDeliveryType createProjectDeliveryType(SaveDictionaryRequest request, User byWho) {
		if (projectDeliveryTypeRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的项目交付类型");
		}

		ProjectDeliveryType result = new ProjectDeliveryType();
		BeanUtils.copyProperties(request, result);
		result.setId(null);
		result.setModifiedBy(byWho);
		result.setModifiedAt(new Date());

		return projectDeliveryTypeRepository.save(result);
	}

	@Override
	public ProjectDeliveryType updateProjectDeliveryType(String id, SaveDictionaryRequest request, User byWho) {
		ProjectDeliveryType projectDeliveryType = projectDeliveryTypeRepository.findById(id);
		if (projectDeliveryType == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		ProjectDeliveryType matchedProjectDeliveryType = projectDeliveryTypeRepository.findFirstByName(request.getName());
		if (matchedProjectDeliveryType != null && !matchedProjectDeliveryType.getId().equals(id)) {
			throw new ValidationException("重复的项目交付类型");
		}

		projectDeliveryType.setName(request.getName());
		projectDeliveryType.setDescription(request.getDescription());
		projectDeliveryType.setModifiedBy(byWho);
		projectDeliveryType.setModifiedAt(new Date());

		return projectDeliveryTypeRepository.save(projectDeliveryType);
	}

	@Override
	public ProjectDeliveryType findProjectDeliveryTypeById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return projectDeliveryTypeRepository.findById(id);
	}

	@Override
	public Page<ProjectDeliveryType> listProjectDeliveryTypes(PageQueryRequest request) {
		return projectDeliveryTypeRepository.findAll(new PageRequest(request.getStart(), request.getLimit()));
	}

	@Override
	public ContractType createContractType(SaveDictionaryRequest request, User byWho) {
		if (contractTypeRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的合同类型");
		}

		ContractType result = new ContractType();
		BeanUtils.copyProperties(request, result);

		result.setId(null);
		result.setModifiedBy(byWho);
		result.setModifiedAt(new Date());

		return contractTypeRepository.save(result);
	}

	@Override
	public ContractType updateContractType(String id, SaveDictionaryRequest request, User byWho) {
		ContractType contractType = contractTypeRepository.findById(id);
		if (contractType == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		ContractType matchedContractType = contractTypeRepository.findFirstByName(request.getName());
		if (matchedContractType != null && !matchedContractType.getId().equals(id)) {
			throw new ValidationException("重复的合同类型");
		}

		contractType.setName(request.getName());
		contractType.setDescription(request.getDescription());
		contractType.setModifiedBy(byWho);
		contractType.setModifiedAt(new Date());

		return contractTypeRepository.save(contractType);
	}

	@Override
	public ContractType findContractTypeById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return contractTypeRepository.findById(id);
	}

	@Override
	public Page<ContractType> listContractTypes(PageQueryRequest request) {
		return contractTypeRepository.findAll(new PageRequest(request.getStart(), request.getLimit()));
	}

	@Override
	public DepositType createDepositType(SaveDictionaryRequest request, User byWho) {
		if (depositTypeRepository.findFirstByName(request.getName()) != null) {
			throw new ValidationException("重复的保证金类型");
		}

		DepositType result = new DepositType();
		BeanUtils.copyProperties(request, result);

		result.setId(null);
		result.setModifiedBy(byWho);
		result.setModifiedAt(new Date());

		return depositTypeRepository.save(result);
	}

	@Override
	public DepositType updateDepositType(String id, SaveDictionaryRequest request, User byWho) {
		DepositType depositType = depositTypeRepository.findById(id);
		if (depositType == null) {
			throw new EntityNotFoundException("没有找到id对应的数据");
		}

		DepositType matchedDepositType = depositTypeRepository.findFirstByName(request.getName());
		if (matchedDepositType != null && !matchedDepositType.getId().equals(id)) {
			throw new ValidationException("重复的保证金类型");
		}

		depositType.setName(request.getName());
		depositType.setDescription(request.getDescription());
		depositType.setModifiedBy(byWho);
		depositType.setModifiedAt(new Date());

		return depositTypeRepository.save(depositType);
	}

	@Override
	public DepositType findDepositTypeById(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		return depositTypeRepository.findById(id);
	}

	@Override
	public Page<DepositType> listDepositTypes(PageQueryRequest request) {
		return depositTypeRepository.findAll(new PageRequest(request.getStart(), request.getLimit()));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ProjectType projectType = projectTypeRepository.findById("1");
		if (projectType == null) {
			projectType = new ProjectType();
			projectType.setId("1");
			projectType.setName("工程类");

			projectTypeRepository.save(projectType);
		}
		projectType = projectTypeRepository.findById("2");
		if (projectType == null) {
			projectType = new ProjectType();
			projectType.setId("2");
			projectType.setName("资源类");

			projectTypeRepository.save(projectType);
		}
		projectType = projectTypeRepository.findById("3");
		if (projectType == null) {
			projectType = new ProjectType();
			projectType.setId("3");
			projectType.setName("技术服务类");

			projectTypeRepository.save(projectType);
		}

		ProjectDeliveryType projectDeliveryType = projectDeliveryTypeRepository.findById("1");
		if (projectDeliveryType == null) {
			projectDeliveryType = new ProjectDeliveryType();
			projectDeliveryType.setId("1");
			projectDeliveryType.setName("内部承包");

			projectDeliveryTypeRepository.save(projectDeliveryType);
		}
		projectDeliveryType = projectDeliveryTypeRepository.findById("2");
		if (projectDeliveryType == null) {
			projectDeliveryType = new ProjectDeliveryType();
			projectDeliveryType.setId("2");
			projectDeliveryType.setName("外部承包");

			projectDeliveryTypeRepository.save(projectDeliveryType);
		}
		projectDeliveryType = projectDeliveryTypeRepository.findById("3");
		if (projectDeliveryType == null) {
			projectDeliveryType = new ProjectDeliveryType();
			projectDeliveryType.setId("3");
			projectDeliveryType.setName("外部挂靠");

			projectDeliveryTypeRepository.save(projectDeliveryType);
		}
		projectDeliveryType = projectDeliveryTypeRepository.findById("4");
		if (projectDeliveryType == null) {
			projectDeliveryType = new ProjectDeliveryType();
			projectDeliveryType.setId("4");
			projectDeliveryType.setName("内部自营");

			projectDeliveryTypeRepository.save(projectDeliveryType);
		}

		ContractType contractType = contractTypeRepository.findById("1");
		if (contractType == null) {
			contractType = new ContractType();
			contractType.setId("1");
			contractType.setName("框架合同");

			contractTypeRepository.save(contractType);
		}
		contractType = contractTypeRepository.findById("2");
		if (contractType == null) {
			contractType = new ContractType();
			contractType.setId("2");
			contractType.setName("正式合同");

			contractTypeRepository.save(contractType);
		}

		DepositType depositType = depositTypeRepository.findById("1");
		if (depositType == null) {
			depositType = new DepositType();
			depositType.setId("1");
			depositType.setName("投标保证金");

			depositTypeRepository.save(depositType);
		}

		depositType = depositTypeRepository.findById("2");
		if (depositType == null) {
			depositType = new DepositType();
			depositType.setId("2");
			depositType.setName("履约保证金");

			depositTypeRepository.save(depositType);
		}

		depositType = depositTypeRepository.findById("3");
		if (depositType == null) {
			depositType = new DepositType();
			depositType.setId("3");
			depositType.setName("施工保证金");

			depositTypeRepository.save(depositType);
		}


	}
}
