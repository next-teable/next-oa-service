package cn.com.starest.nextoa.project.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 合同（框架合同和正式合同）
 *
 * @author dz
 */
@Document(collection = "ContractTypes")
public class ContractType extends AbstractType {

}
