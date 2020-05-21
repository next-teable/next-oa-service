package cn.com.starest.nextoa.project.domain.request;

/**
 * Created by cengruilin on 2017/12/28.
 */
public interface SaveProjectManageRelationshipRequest {

    Integer getYear();

    String getBizDepartmentId();

    String getProjectId();

    String[] getManagerIds();
}
