package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dz
 */
public interface ProjectRepositoryCustom {

	void clearSort(Project project);

	void sortProject(Project project, String sort);

	Page<Project> queryPage(ProjectQueryRequest request);

	Page<Project> queryPageByManager(ProjectQueryRequest request, User manager);

	Page<Project> queryPage4SupervisedMonitorProject(ProjectQueryRequest queryRequest);

	Page<Project> queryPage4ManagedMonitorProject(ProjectQueryRequest queryRequest, User manager);

	void setProjectManagers(String projectId, List<User> managers);

	void setReducedPercent(String projectId, BigDecimal reducedPercent);
}
