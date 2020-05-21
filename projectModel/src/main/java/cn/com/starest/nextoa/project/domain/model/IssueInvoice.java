package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 开票
 *
 * @author dz
 */
@Document(collection = "IssueInvoices")
public class IssueInvoice extends BaseModel {

    //票据类型*
    private InvoiceType type;

    //项目名称（区域）*
    @Indexed
    @DBRef(lazy = true)
    private Project project;

    //主合同/协议名称*
    @Indexed
    @DBRef(lazy = true)
    private Contract contract;

    //主订单
    @Indexed
    @DBRef(lazy = true)
    private Order order;

    /**
     * added at 20180102, 为了方便小项目监管和事业部门监管的统计视图
     */
    private int orderYear; //从订单year字段复制的冗余字段,方便统计

    //开票单位
    @Indexed
    @DBRef(lazy = true)
    private FirstParty firstParty;

    //公司
    @Indexed
    @DBRef(lazy = true)
    private Company company;

    //开票金额*
    private BigDecimal amount;

    //开票类型*
    private BigDecimal taxRate;

    //开票日期*
    private Date handleDate;

    private String description;

    public InvoiceType getType() {
        return type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public FirstParty getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(FirstParty firstParty) {
        this.firstParty = firstParty;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderYear() {
        return orderYear;
    }

    public void setOrderYear(int orderYear) {
        this.orderYear = orderYear;
    }
}
