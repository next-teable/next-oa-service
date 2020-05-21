package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Import Salary
 *
 * @author dz
 */
@Document(collection = "SalaryImportHistories")
public class SalaryImportHistory extends BaseModel {

}
