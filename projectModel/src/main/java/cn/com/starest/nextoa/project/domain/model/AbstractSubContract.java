package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class AbstractSubContract extends BaseModel {
    
    // unique 主合同编号* 格式：分包单位简称-年月日-编号
    @Indexed
    private String code;
    
    // unique 主合同/协议名称*
    @Indexed
    private String name;
    
    // 项目名称（区域）*
    @Indexed
    @DBRef(lazy = true)
    private Project project;
    
    // 主合同/协议名称*
    @Indexed
    @DBRef(lazy = true)
    private Contract contract;
    
    // 分包单位名称*
    @Indexed
    @DBRef(lazy = true)
    private SubContractor subContractor;
    
    // 分包单位联系人
    private String contactName;
    
    // 分包单位联系电话
    private String contactPhone;
    
    // 分包比例
    private BigDecimal percent;
    
    private String description;
    
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
    
    public Project getProject() {
        return project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    
    public Contract getContract() {
        return contract;
    }
    
    public void setContract(Contract contract) {
        this.contract = contract;
    }
    
    public SubContractor getSubContractor() {
        return subContractor;
    }
    
    public void setSubContractor(SubContractor subContractor) {
        this.subContractor = subContractor;
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
