package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dz
 */
@Document(collection = "ProjectSettings")
public class ProjectSetting extends StringIdentifier {

    @DBRef
    private List<User> supervisors = new ArrayList<>();

    public List<User> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<User> supervisors) {
        this.supervisors = supervisors;
    }

}
