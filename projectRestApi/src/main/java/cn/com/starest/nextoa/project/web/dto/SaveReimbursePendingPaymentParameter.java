package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveReimbursePendingPaymentRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class SaveReimbursePendingPaymentParameter implements SaveReimbursePendingPaymentRequest {

    //待付款id
    String pendingPaymentId;

    BigDecimal amount;

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String getPendingPaymentId() {
        return pendingPaymentId;
    }

    public void setPendingPaymentId(String pendingPaymentId) {
        this.pendingPaymentId = pendingPaymentId;
    }

}
