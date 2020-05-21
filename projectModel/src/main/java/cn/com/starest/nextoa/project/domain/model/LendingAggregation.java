package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 借(还)款 - 按借款单位和借款人汇总最终结果
 *
 * @author dz
 */
@Document(collection = "LendingAggregations")
public class LendingAggregation extends StringIdentifier {
    
    // 借款对象
    @Indexed
    private LendingObject lendingObject;
    
    // 借款人
    @Indexed
    @DBRef(lazy = true)
    private User lendingBy;
    
    // 借款公司
    @Indexed
    @DBRef(lazy = true)
    private Company lendingTo;
    
    // 借款金额
    private BigDecimal lendingAmount;
    
    // 还款金额
    private BigDecimal repaymentAmount;
    
    // 欠款金额
    private BigDecimal owedAmount;
    
    private Date modifiedAt;
    
    public LendingObject getLendingObject() {
        return lendingObject;
    }
    
    public void setLendingObject(LendingObject lendingObject) {
        this.lendingObject = lendingObject;
    }
    
    public User getLendingBy() {
        return lendingBy;
    }
    
    public void setLendingBy(User lendingBy) {
        this.lendingBy = lendingBy;
    }
    
    public Company getLendingTo() {
        return lendingTo;
    }
    
    public void setLendingTo(Company lendingTo) {
        this.lendingTo = lendingTo;
    }
    
    public BigDecimal getLendingAmount() {
        return lendingAmount;
    }
    
    public void setLendingAmount(BigDecimal lendingAmount) {
        this.lendingAmount = lendingAmount;
    }
    
    public BigDecimal getRepaymentAmount() {
        return repaymentAmount;
    }
    
    public void setRepaymentAmount(BigDecimal repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }
    
    public BigDecimal getOwedAmount() {
        return owedAmount;
    }
    
    public void setOwedAmount(BigDecimal owedAmount) {
        this.owedAmount = owedAmount;
    }
    
    public Date getModifiedAt() {
        return modifiedAt;
    }
    
    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
    
}
