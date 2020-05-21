package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.LendingObject;
import cn.com.starest.nextoa.project.domain.model.LendingType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public interface SaveLendingRequest {
    
    // 公司
    String getCompanyId();

    // 借款类型
    LendingType getLendingType();
    
    // 借款金额
    BigDecimal getAmount();

    // 借款对象
    LendingObject getLendingObject();

    // 借款人
    String getLendingById();
    
    // 借款公司（分包公司）
    String getLendingToId();
    
    // 借款日期
    Date getHandleAt();
    
    // 借款原因
    String getReason();
    
    // 备注
    String getDescription();
    
}
