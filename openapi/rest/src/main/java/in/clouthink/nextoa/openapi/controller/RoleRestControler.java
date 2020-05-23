package in.clouthink.nextoa.openapi.controller;

import cn.com.starest.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.support.RoleRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("角色管理")
@RestController
@RequestMapping("/api/roles")
public class RoleRestControler {
    
    @Autowired
    private RoleRestSupport roleRestSupport;
    
    @ApiOperation(value = "获取内置角色（角色管理）")
    @RequestMapping(value = "/sysroles", method = RequestMethod.GET)
    public List<RoleSummary> getSysRoles() {
        return roleRestSupport.getSysRoles();
    }

    @ApiOperation(value = "获取内置角色(权限管理)")
    @RequestMapping(value = "/sysroles/privilege", method = RequestMethod.GET)
    public List<RoleSummary> getSysRoles4Privilege() {
        return roleRestSupport.getSysRoles4Privilege();
    }
    
    @ApiOperation(value = "获取新增角色")
    @RequestMapping(value = "/approles", method = RequestMethod.GET)
    public Page<RoleSummary> getAppRoles(RoleQueryParameter request) {
        return roleRestSupport.getAppRoles(request);
    }

    @ApiOperation(value = "获取新增角色(不分页)")
    @RequestMapping(value = "/approles/list", method = RequestMethod.GET)
    public List<RoleSummary> getAppRolesList() {
        return roleRestSupport.getAppRolesList();
    }
    
    @ApiOperation(value = "获取内置角色对应用户")
    @RequestMapping(value = "/sysroles/{id}/users", method = RequestMethod.GET)
    public Page<UserSummary> getUsersBySysRoleId(@PathVariable String id,
                                                 UserQueryParameter request) {
        return roleRestSupport.getUsersBySysRoleId(id, request);
    }
    
    @ApiOperation(value = "获取新增角色对应用户")
    @RequestMapping(value = "/approles/{id}/users", method = RequestMethod.GET)
    public Page<UserSummary> getUsersByAppRoleId(@PathVariable String id,
                                                 UserQueryParameter request) {
        return roleRestSupport.getUsersByAppRoleId(id, request);
    }
    
    @ApiOperation(value = "新增角色")
    @RequestMapping(value = "/approles", method = RequestMethod.POST)
    public String createAppRole(@RequestBody SaveRoleParameter request) {
        return roleRestSupport.createAppRole(request).getId();
    }
    
    @ApiOperation(value = "更新角色")
    @RequestMapping(value = "/approles/{id}", method = RequestMethod.POST)
    public void updateAppRole(@PathVariable String id,
                              @RequestBody SaveRoleParameter request) {
        roleRestSupport.updateAppRole(id, request);
    }
    
    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "/approles/{id}", method = RequestMethod.DELETE)
    public void deleteAppRole(@PathVariable String id) {
        roleRestSupport.deleteAppRole(id);
    }
    
    @ApiOperation(value = "为角色绑定用户")
    @RequestMapping(value = "/approles/{id}/bindUsers", method = RequestMethod.POST)
    public void bindUsers4AppRole(@PathVariable String id,
                          @RequestBody UsersForRoleParameter request) {
        roleRestSupport.bindUsers4AppRole(id, request);
    }
    
    @ApiOperation(value = "为角色解绑用户")
    @RequestMapping(value = "/approles/{id}/unBindUsers", method = RequestMethod.POST)
    public void unBindUsers4AppRole(@PathVariable String id,
                            @RequestBody UsersForRoleParameter request) {
        roleRestSupport.unBindUsers4AppRole(id, request);
    }

    @ApiOperation(value = "为角色绑定用户")
    @RequestMapping(value = "/sysroles/{id}/bindUsers", method = RequestMethod.POST)
    public void bindUsers4SysRole(@PathVariable String id,
                          @RequestBody UsersForRoleParameter request) {
        roleRestSupport.bindUsers4SysRole(id, request);
    }

    @ApiOperation(value = "为角色解绑用户")
    @RequestMapping(value = "/sysroles/{id}/unBindUsers", method = RequestMethod.POST)
    public void unBindUsers4SysRole(@PathVariable String id,
                            @RequestBody UsersForRoleParameter request) {
        roleRestSupport.unBindUsers4SysRole(id, request);
    }
}
