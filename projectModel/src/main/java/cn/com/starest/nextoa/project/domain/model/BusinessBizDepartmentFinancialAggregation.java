package cn.com.starest.nextoa.project.domain.model;

/**
 * Created by cengruilin on 2018/1/3.
 */
public class BusinessBizDepartmentFinancialAggregation {
    String bizDepartmentId;
    String bizDepartmentName;
    private int year;
    private BizDepartmentFinancialAggregation departmentFinancialAggregation;
    private ProjectFinancialAggregation projectFinancialAggregation;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BizDepartmentFinancialAggregation getDepartmentFinancialAggregation() {
        return departmentFinancialAggregation;
    }

    public void setDepartmentFinancialAggregation(BizDepartmentFinancialAggregation departmentFinancialAggregation) {
        this.departmentFinancialAggregation = departmentFinancialAggregation;
    }

    public ProjectFinancialAggregation getProjectFinancialAggregation() {
        return projectFinancialAggregation;
    }

    public void setProjectFinancialAggregation(ProjectFinancialAggregation projectFinancialAggregation) {
        this.projectFinancialAggregation = projectFinancialAggregation;
    }

    public String getBizDepartmentId() {
        return bizDepartmentId;
    }

    public void setBizDepartmentId(String bizDepartmentId) {
        this.bizDepartmentId = bizDepartmentId;
    }

    public String getBizDepartmentName() {
        return bizDepartmentName;
    }

    public void setBizDepartmentName(String bizDepartmentName) {
        this.bizDepartmentName = bizDepartmentName;
    }
}
