package in.clouthink.nextoa.openapi.startup;

import in.clouthink.nextoa.bl.model.SysRole;
import in.clouthink.nextoa.bl.model.SystemSetting;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserType;
import in.clouthink.nextoa.bl.openapi.dto.SaveSysUserParameter;
import in.clouthink.nextoa.bl.openapi.dto.SaveSystemSettingParameter;
import in.clouthink.nextoa.bl.service.AccountService;
import in.clouthink.nextoa.bl.service.SystemSettingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApplicationStartupProperties.class)
public class ApplicationStartup implements InitializingBean {

    private static final Log logger = LogFactory.getLog(ApplicationStartup.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private SystemSettingService systemSettingService;

    @Autowired
    private ApplicationStartupProperties applicationStartupProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        initializeApplication();
    }

    private void initializeApplication() {
        // initialize System Administrator
        ApplicationStartupProperties.Administrator administrator = applicationStartupProperties.getAdministrator();
        User adminUser = accountService.findByUsername(administrator.getUsername());
        if (adminUser != null) {
            logger.debug("The admin user is existed, we will skip it");
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
            logger.debug("The system setting is existed, we will skip it");
        } else {
            SaveSystemSettingParameter parameter = new SaveSystemSettingParameter();
            parameter.setName("NEXTOA");
            systemSettingService.updateSystemSetting(parameter, null);
        }
    }
}
