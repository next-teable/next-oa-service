package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.Lending;
import cn.com.starest.nextoa.project.domain.model.LendingObject;
import cn.com.starest.nextoa.project.domain.model.LendingType;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class LendingSummary extends AbstractPermissionAware {

    public static void convert(Lending fromObj, LendingSummary result) {
        AbstractBaseSummary.convert(fromObj, result);
        BeanUtils.copyProperties(fromObj, result);

        if (fromObj.getCompany() != null) {
            result.setCompanyId(fromObj.getCompany().getId());
            result.setCompanyName(fromObj.getCompany().getName());
        }
        if (fromObj.getLendingBy() != null) {
            result.setLendingById(fromObj.getLendingBy().getId());
            result.setLendingByName(fromObj.getLendingBy().getUsername());
        }
        if (fromObj.getLendingTo() != null) {
            result.setLendingToId(fromObj.getLendingTo().getId());
            result.setLendingToName(fromObj.getLendingTo().getName());
        }
        result.setAmount(fromObj.getAmount().abs());
    }

    public static LendingSummary from(Lending fromObj) {
        if (fromObj == null) {
            return null;
        }
        LendingSummary result = new LendingSummary();
        convert(fromObj, result);
        return result;
    }

    private String companyId;

    private String companyName;

    // 借款对象
    private LendingObject lendingObject;

    // 借款类型
    private LendingType lendingType;

    // 借款金额
    private BigDecimal amount;

    // 借款人
    private String lendingById;

    private String lendingByName;

    // 借款公司 - 分包公司
    private String lendingToId;

    private String lendingToName;

    // 借款日期
    private Date handleAt;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public LendingObject getLendingObject() {
        return lendingObject;
    }

    public void setLendingObject(LendingObject lendingObject) {
        this.lendingObject = lendingObject;
    }

    public LendingType getLendingType() {
        return lendingType;
    }

    public void setLendingType(LendingType lendingType) {
        this.lendingType = lendingType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLendingById() {
        return lendingById;
    }

    public void setLendingById(String lendingById) {
        this.lendingById = lendingById;
    }

    public String getLendingByName() {
        return lendingByName;
    }

    public void setLendingByName(String lendingByName) {
        this.lendingByName = lendingByName;
    }

    public String getLendingToId() {
        return lendingToId;
    }

    public void setLendingToId(String lendingToId) {
        this.lendingToId = lendingToId;
    }

    public String getLendingToName() {
        return lendingToName;
    }

    public void setLendingToName(String lendingToName) {
        this.lendingToName = lendingToName;
    }

    public Date getHandleAt() {
        return handleAt;
    }

    public void setHandleAt(Date handleAt) {
        this.handleAt = handleAt;
    }
}
