package cn.com.starest.nextoa.project.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 保证金类型（枚举值：投标保证金，履约保证金）
 *
 * @author dz
 */
@Document(collection = "DepositTypes")
public class DepositType extends AbstractType {

}
