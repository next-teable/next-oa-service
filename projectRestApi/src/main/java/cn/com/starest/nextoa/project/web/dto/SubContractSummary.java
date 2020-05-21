package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.AbstractSubContract;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.SubContract;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class SubContractSummary extends AbstractPermissionAware {
    
    public static void convert(AbstractSubContract fromObj,
                               SubContractSummary result) {
        AbstractBaseSummary.convert(fromObj, result);
        BeanUtils.copyProperties(fromObj, result);

        if (fromObj.getProject() != null) {
            result.setProjectId(fromObj.getProject().getId());
            result.setProjectName(fromObj.getProject().getName());
        }
        if (fromObj.getContract() != null) {
            result.setContractId(fromObj.getContract().getId());
            result.setContractName(Contract.getContractName(fromObj.getContract()));
        }
        if (fromObj.getSubContractor() != null) {
            result.setSubContractorId(fromObj.getSubContractor().getId());
            result.setSubContractorName(fromObj.getSubContractor().getName());
        }
        if (fromObj instanceof SubContract) {
            convert((SubContract) fromObj, result);
        }
    }
    
    public static void convert(SubContract fromObj, SubContractSummary result) {
        
        if (fromObj.getPublishBy() != null) {
            result.setPublishById(fromObj.getPublishBy().getId());
            result.setPublishByName(fromObj.getPublishBy().getUsername());
        }
    }
    
    public static SubContractSummary from(SubContract fromObj) {
        if (fromObj == null) {
            return null;
        }
        SubContractSummary result = new SubContractSummary();
        convert((AbstractSubContract) fromObj, result);
        return result;
    }
    
    // unique 主合同编号* 格式：分包单位简称-年月日-编号
    private String code;
    
    // unique 主合同/协议名称*
    private String name;
    
    // 项目名称（区域）*
    private String projectId;
    
    private String projectName;
    
    // 主合同/协议名称*
    private String contractId;
    
    private String contractName;
    
    // 分包单位名称*
    private String subContractorId;
    
    private String subContractorName;
    
    // 分包比例
    private BigDecimal percent;

    // 分包单位联系人
    private String contactName;

    // 分包单位联系电话
    private String contactPhone;
    
    private String description;
    
    private boolean published;
    
    private Date publishAt;
    
    private String publishById;
    
    private String publishByName;
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getProjectId() {
        return projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public String getContractId() {
        return contractId;
    }
    
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    
    public String getContractName() {
        return contractName;
    }
    
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }
    
    public String getSubContractorId() {
        return subContractorId;
    }
    
    public void setSubContractorId(String subContractorId) {
        this.subContractorId = subContractorId;
    }
    
    public String getSubContractorName() {
        return subContractorName;
    }
    
    public void setSubContractorName(String subContractorName) {
        this.subContractorName = subContractorName;
    }
    
    public BigDecimal getPercent() {
        return percent;
    }
    
    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isPublished() {
        return published;
    }
    
    public void setPublished(boolean published) {
        this.published = published;
    }
    
    public Date getPublishAt() {
        return publishAt;
    }
    
    public void setPublishAt(Date publishAt) {
        this.publishAt = publishAt;
    }
    
    public String getPublishById() {
        return publishById;
    }
    
    public void setPublishById(String publishById) {
        this.publishById = publishById;
    }
    
    public String getPublishByName() {
        return publishByName;
    }
    
    public void setPublishByName(String publishByName) {
        this.publishByName = publishByName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
