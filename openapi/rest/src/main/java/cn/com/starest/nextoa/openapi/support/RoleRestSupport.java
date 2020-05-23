package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.AppRole;
import cn.com.starest.nextoa.model.dtr.RoleQueryRequest;
import cn.com.starest.nextoa.openapi.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleRestSupport {
    
    List<RoleSummary> getSysRoles();

    List<RoleSummary> getSysRoles4Privilege();

    Page<RoleSummary> getAppRoles(RoleQueryRequest request);

    List<RoleSummary> getAppRolesList();

    Page<UserSummary> getUsersBySysRoleId(String roleId,
                                          UserQueryParameter request);
    
    Page<UserSummary> getUsersByAppRoleId(String roleId,
                                          UserQueryParameter request);

    AppRole createAppRole(SaveRoleParameter request);
    
    void updateAppRole(String id, SaveRoleParameter request);
    
    void deleteAppRole(String id);
    
    void bindUsers4AppRole(String id, UsersForRoleParameter request);
    
    void unBindUsers4AppRole(String id, UsersForRoleParameter request);
    
    void bindUsers4SysRole(String id, UsersForRoleParameter request);
    
    void unBindUsers4SysRole(String id, UsersForRoleParameter request);
    
}
