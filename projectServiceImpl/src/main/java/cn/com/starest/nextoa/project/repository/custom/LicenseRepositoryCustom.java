package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.License;
import cn.com.starest.nextoa.project.domain.request.LicenseQueryRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface LicenseRepositoryCustom {

	Page<License> queryPage(LicenseQueryRequest request);

}
