package cn.com.starest.nextoa.dashboard;

import cn.com.starest.nextoa.model.SysRole;
import cn.com.starest.nextoa.model.SystemSetting;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserType;
import cn.com.starest.nextoa.openapi.dto.SaveSysUserParameter;
import cn.com.starest.nextoa.openapi.dto.SaveSystemSettingParameter;
import cn.com.starest.nextoa.project.domain.model.IssueInvoice;
import cn.com.starest.nextoa.project.repository.IssueInvoiceRepository;
import cn.com.starest.nextoa.service.AccountService;
import cn.com.starest.nextoa.service.SystemSettingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DashboardStartupCallback implements InitializingBean {

    private static final Log logger = LogFactory.getLog(DashboardStartupCallback.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private SystemSettingService systemSettingService;

    @Autowired
    private DashboardConfigurationProperties dashboardConfigurationProperties;

    @Autowired
    private IssueInvoiceRepository issueInvoiceRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        initializeApplication();
    }

    private void initializeApplication() {
        // initialize System Administrator
        DashboardConfigurationProperties.Administrator administrator = dashboardConfigurationProperties.getAdministrator();
        User adminUser = accountService.findByUsername(administrator.getUsername());
        if (adminUser != null) {
            logger.debug("The admin user is created before, we will skip it");
        } else {
            SaveSysUserParameter saveSysUserParameter = new SaveSysUserParameter();
            saveSysUserParameter.setEmail(administrator.getEmail());
            saveSysUserParameter.setUsername(administrator.getUsername());
            saveSysUserParameter.setPassword(administrator.getPassword());
            saveSysUserParameter.setContactPhone(administrator.getCellphone());
            accountService.createAccount(UserType.SYSUSER, saveSysUserParameter, SysRole.ROLE_USER, SysRole.ROLE_ADMIN);
        }

        SystemSetting systemSetting = systemSettingService.getSystemSetting();
        if (systemSetting != null) {
            logger.debug("The system setting is initialized before, we will skip it");
        } else {
            SaveSystemSettingParameter parameter = new SaveSystemSettingParameter();
            parameter.setName("星辰佳OA系统");
            systemSettingService.updateSystemSetting(parameter, null);
        }

        //set IssueInvoice yearOfOrder 20180102
        List<IssueInvoice> notSetOrderYearIssueInvoiceList = issueInvoiceRepository.findByOrderYearIsNull();
        for (IssueInvoice issueInvoice : notSetOrderYearIssueInvoiceList) {
            if (issueInvoice.getOrder() != null) {
                issueInvoice.setOrderYear(issueInvoice.getOrder().getYear());
                issueInvoiceRepository.save(issueInvoice);
            }
        }
    }
}
