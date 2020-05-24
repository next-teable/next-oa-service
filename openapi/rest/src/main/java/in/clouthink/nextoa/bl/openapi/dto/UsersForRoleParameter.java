package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.request.UsersForRoleRequest;

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
