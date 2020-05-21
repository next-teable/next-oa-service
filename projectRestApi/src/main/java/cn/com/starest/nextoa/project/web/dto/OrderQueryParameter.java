package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.request.OrderQueryRequest;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class OrderQueryParameter extends PageQueryParameter implements OrderQueryRequest {

    String name;

    String code;

    String projectId;

    String contractId;

    Boolean published;

    BigDecimal minAmount;

    BigDecimal maxAmount;

    String province;

    String city;

    @Override
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Override
    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    @Override
    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    @Override
    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }
}
