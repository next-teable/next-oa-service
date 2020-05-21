package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务部门
 *
 * @author dz
 */
@Document(collection = "BizDepartments")
public class BizDepartment extends BaseModel {

    @Indexed
    private String name;

    private String description;

    private String sort;

    //部门主管 （ 至少一个 ）
    @Indexed
    @DBRef(lazy = true)
    private List<User> managers = new ArrayList();

    private BizDepartmentType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<User> getManagers() {
        return managers;
    }

    public void setManagers(List<User> managers) {
        this.managers = managers;
    }

    public BizDepartmentType getType() {
        return type;
    }

    public void setType(BizDepartmentType type) {
        this.type = type;
    }
}
