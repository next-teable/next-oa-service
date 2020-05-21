package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cengruilin on 2017/12/28.
 */
@Document(collection = "ProjectManageRelationships")
public class ProjectManageRelationship extends BaseModel {

    @Indexed
    private int year;

    @Indexed
    @DBRef(lazy = true)
    private Project project;

    @Indexed
    @DBRef(lazy = true)
    private BizDepartment belongTo;

    //项目经理 （ 至少一个 ）
    @Indexed
    @DBRef(lazy = true)
    private List<User> managers = new ArrayList();

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public BizDepartment getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(BizDepartment belongTo) {
        this.belongTo = belongTo;
    }

    public List<User> getManagers() {
        return managers;
    }

    public void setManagers(List<User> managers) {
        this.managers = managers;
    }
}
