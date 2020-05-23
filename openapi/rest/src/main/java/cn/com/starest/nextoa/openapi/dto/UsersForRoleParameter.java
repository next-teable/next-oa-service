package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.dtr.UsersForRoleRequest;

import java.util.List;

public class UsersForRoleParameter implements UsersForRoleRequest {
    
    private List<String> userIds;
    
    @Override
    public List<String> getUserIds() {
        return userIds;
    }
    
    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
