package in.clouthink.nextoa.bl.service.impl;

import in.clouthink.nextoa.bl.exception.SystemSettingException;
import in.clouthink.nextoa.bl.model.BaseModel;
import in.clouthink.nextoa.bl.model.ShortUrl;
import in.clouthink.nextoa.bl.model.SystemSetting;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.repository.ShortUrlRepository;
import in.clouthink.nextoa.bl.repository.SystemSettingRepository;
import in.clouthink.nextoa.bl.request.SaveSystemSettingRequest;
import in.clouthink.nextoa.bl.service.AccountService;
import in.clouthink.nextoa.bl.service.SystemSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Service
public class SystemSettingServiceImpl implements SystemSettingService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private SystemSettingRepository systemSettingRepository;

	@Autowired
	private ShortUrlRepository shortUrlRepository;

	@Override
	public SystemSetting getSystemSetting() {
		return systemSettingRepository.findById(SystemSetting.DEFAULT_ID);
	}

	@Override
	public void updateSystemSetting(SaveSystemSettingRequest request, User byWho) {
		if (StringUtils.isEmpty(request.getName())) {
			throw new SystemSettingException("系统名称不能为空");
		}

		String[] profitSupervisorIds = request.getProfitSupervisorIds();
		if (profitSupervisorIds == null || profitSupervisorIds.length != 3) {
			throw new SystemSettingException("请设置三位提成利润的领导");
		}

		if (StringUtils.isEmpty(request.getProfitAccountSubjectName())) {
			throw new SystemSettingException("请设置利润提成报销的会计科目名称");
		}

		if (StringUtils.isEmpty(request.getOutsourceAccountSubjectName())) {
			throw new SystemSettingException("请设置外协单位报销的会计科目名称");
		}

		SystemSetting existedSystemSetting = getSystemSetting();
		if (existedSystemSetting == null) {
			existedSystemSetting = new SystemSetting();
			existedSystemSetting.setId(SystemSetting.DEFAULT_ID);
			BaseModel.onCreate(existedSystemSetting, byWho);
		}

		BeanUtils.copyProperties(request, existedSystemSetting);
		existedSystemSetting.setCompanySupervisors(Arrays.asList(request.getCompanySupervisorIds())
														 .stream()
														 .map(userId -> accountService.findById(userId))
														 .collect(Collectors.toList()));
		existedSystemSetting.setBizDepartmentSupervisors(Arrays.asList(request.getBizDepartmentSupervisorIds())
															   .stream()
															   .map(userId -> accountService.findById(userId))
															   .collect(Collectors.toList()));
		existedSystemSetting.setProjectSupervisors(Arrays.asList(request.getProjectSupervisorIds())
														 .stream()
														 .map(userId -> accountService.findById(userId))
														 .collect(Collectors.toList()));

		existedSystemSetting.setProfitSupervisors(Arrays.asList(request.getProfitSupervisorIds())
														.stream()
														.map(userId -> accountService.findById(userId))
														.collect(Collectors.toList()));
		BaseModel.onModify(existedSystemSetting, byWho);

		systemSettingRepository.save(existedSystemSetting);
	}


	@Override
	public String getFullUrl(String shortUrlKey) {
		ShortUrl shortUrl = shortUrlRepository.findFirstByKey(shortUrlKey);
		if (shortUrl == null) {
			return null;
		}
		return shortUrl.getUrl();
	}


	@Override
	public List<User> listProjectSupervisors() {
		SystemSetting projectSetting = systemSettingRepository.findById(SystemSetting.DEFAULT_ID);
		return projectSetting.getProjectSupervisors();
	}

	@Override
	public boolean isProjectSupervisor(User user) {
		SystemSetting projectSetting = systemSettingRepository.findById(SystemSetting.DEFAULT_ID);
		if (projectSetting.getProjectSupervisors() != null) {
			for (User item : projectSetting.getProjectSupervisors()) {
				if (user.getId().equals(item.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<User> listCompanySupervisors() {
		SystemSetting projectSetting = systemSettingRepository.findById(SystemSetting.DEFAULT_ID);
		return projectSetting.getCompanySupervisors();
	}

	@Override
	public boolean isCompanySupervisor(User user) {
		SystemSetting projectSetting = systemSettingRepository.findById(SystemSetting.DEFAULT_ID);
		if (projectSetting.getCompanySupervisors() != null) {
			for (User item : projectSetting.getCompanySupervisors()) {
				if (user.getId().equals(item.getId())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<User> listBizDepartmentSupervisors() {
		SystemSetting projectSetting = systemSettingRepository.findById(SystemSetting.DEFAULT_ID);
		return projectSetting.getBizDepartmentSupervisors();
	}

	@Override
	public boolean isBizDepartmentSupervisor(User user) {
		SystemSetting projectSetting = systemSettingRepository.findById(SystemSetting.DEFAULT_ID);
		if (projectSetting.getBizDepartmentSupervisors() != null) {
			for (User item : projectSetting.getBizDepartmentSupervisors()) {
				if (user.getId().equals(item.getId())) {
					return true;
				}
			}
		}
		return false;
	}
}
