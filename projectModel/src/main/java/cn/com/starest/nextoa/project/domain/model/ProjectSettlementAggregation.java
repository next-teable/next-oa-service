package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.User;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class ProjectSettlementAggregation {

    /**
     * @param from
     * @param to
     * @return
     */
    public static ProjectSettlementAggregation add(ProjectSettlementAggregation from, ProjectSettlementAggregation to) {
        from.setProfit1(from.getProfit1().add(to.getProfit1()));
        from.setMarketProfit1(from.getMarketProfit1().add(to.getMarketProfit1()));
        from.setLeftAmount1(from.getLeftAmount1().add(to.getLeftAmount1()));
        from.setWithdrawAmount1(from.getWithdrawAmount1().add(to.getWithdrawAmount1()));

        from.setTotalProfit(from.getProfit1().add(from.getMarketProfit1()));

        return from;
    }

    /**
     * 只处理匹配的用户,并将信息放在user1的位置上
     *
     * @param fromObj
     * @param byWho
     * @return
     */
    public static ProjectSettlementAggregation fromOneUser(ProjectSettlement fromObj, User byWho) {
        ProjectSettlementAggregation result = new ProjectSettlementAggregation();
        result.setId(fromObj.getId());

        if (fromObj.getProject() != null) {
            result.setProjectId(fromObj.getProject().getId());
            result.setProjectName(fromObj.getProject().getName());
        }

        if (fromObj.getOrder() != null) {
            result.setOrderId(fromObj.getOrder().getId());
            result.setOrderName(fromObj.getOrder().getName());
        }

        if (fromObj.getContract() != null) {
            result.setContractId(fromObj.getContract().getId());
            result.setContractName(fromObj.getContract().getName());
        }

        if (fromObj.getUser1() != null && byWho.getId().equals(fromObj.getUser1().getId())) {
            result.setUser1Id(fromObj.getUser1().getId());
            result.setUser1Name(fromObj.getUser1().getUsername());
            result.setPercent1(fromObj.getPercent1());
            result.setMarketPercent1(fromObj.getMarketPercent1());
        }
        else if (fromObj.getUser2() != null && byWho.getId().equals(fromObj.getUser2().getId())) {
            result.setUser1Id(fromObj.getUser2().getId());
            result.setUser1Name(fromObj.getUser2().getUsername());
            result.setPercent1(fromObj.getPercent2());
            result.setMarketPercent1(fromObj.getMarketPercent2());
        }
        else if (fromObj.getUser3() != null && byWho.getId().equals(fromObj.getUser3().getId())) {
            result.setUser1Id(fromObj.getUser3().getId());
            result.setUser1Name(fromObj.getUser3().getUsername());
            result.setPercent1(fromObj.getPercent3());
            result.setMarketPercent1(fromObj.getMarketPercent3());
        }
        else if (fromObj.getUser4() != null && byWho.getId().equals(fromObj.getUser4().getId())) {
            result.setUser1Id(fromObj.getUser4().getId());
            result.setUser1Name(fromObj.getUser4().getUsername());
            result.setPercent1(fromObj.getPercent4());
        }
        else if (fromObj.getUser5() != null && byWho.getId().equals(fromObj.getUser5().getId())) {
            result.setUser1Id(fromObj.getUser5().getId());
            result.setUser1Name(fromObj.getUser5().getUsername());
            result.setPercent1(fromObj.getPercent5());
        }

        return result;
    }


    public static ProjectSettlementAggregation from(ProjectSettlement fromObj) {
        ProjectSettlementAggregation result = new ProjectSettlementAggregation();
        BeanUtils.copyProperties(fromObj, result);

        if (fromObj.getProject() != null) {
            result.setProjectId(fromObj.getProject().getId());
            result.setProjectName(fromObj.getProject().getName());
        }

        if (fromObj.getOrder() != null) {
            result.setOrderId(fromObj.getOrder().getId());
            result.setOrderName(fromObj.getOrder().getName());
        }

        if (fromObj.getContract() != null) {
            result.setContractId(fromObj.getContract().getId());
            result.setContractName(fromObj.getContract().getName());
        }

        if (fromObj.getUser1() != null) {
            result.setUser1Id(fromObj.getUser1().getId());
            result.setUser1Name(fromObj.getUser1().getUsername());
        }

        if (fromObj.getUser2() != null) {
            result.setUser2Id(fromObj.getUser2().getId());
            result.setUser2Name(fromObj.getUser2().getUsername());
        }

        if (fromObj.getUser3() != null) {
            result.setUser3Id(fromObj.getUser3().getId());
            result.setUser3Name(fromObj.getUser3().getUsername());
        }

        if (fromObj.getUser4() != null) {
            result.setUser4Id(fromObj.getUser4().getId());
            result.setUser4Name(fromObj.getUser4().getUsername());
        }

        if (fromObj.getUser5() != null) {
            result.setUser5Id(fromObj.getUser5().getId());
            result.setUser5Name(fromObj.getUser5().getUsername());
        }

        return result;
    }

    private String id;

    private String projectId;

    private String projectName;

    private String contractId;

    private String contractName;

    private String orderId;

    private String orderName;

    private ProjectSettlement.ProjectSettlementStatus status = ProjectSettlement.ProjectSettlementStatus.PENDING;

    private BigDecimal receivedPaymentAmount = BigDecimal.ZERO;

    private String user1Id;

    private String user1Name;

    private BigDecimal percent1 = BigDecimal.ZERO;

    private BigDecimal profit1 = BigDecimal.ZERO;

    private BigDecimal marketPercent1 = BigDecimal.ZERO;

    private BigDecimal marketProfit1 = BigDecimal.ZERO;

    private BigDecimal withdrawAmount1 = BigDecimal.ZERO;

    private BigDecimal leftAmount1 = BigDecimal.ZERO;

    private String user2Id;

    private String user2Name;

    private BigDecimal percent2 = BigDecimal.ZERO;

    private BigDecimal profit2 = BigDecimal.ZERO;

    private BigDecimal marketPercent2 = BigDecimal.ZERO;

    private BigDecimal marketProfit2 = BigDecimal.ZERO;

    private BigDecimal withdrawAmount2 = BigDecimal.ZERO;

    private BigDecimal leftAmount2 = BigDecimal.ZERO;

    private String user3Id;

    private String user3Name;

    private BigDecimal percent3 = BigDecimal.ZERO;

    private BigDecimal profit3 = BigDecimal.ZERO;

    private BigDecimal marketPercent3 = BigDecimal.ZERO;

    private BigDecimal marketProfit3 = BigDecimal.ZERO;

    private BigDecimal withdrawAmount3 = BigDecimal.ZERO;

    private BigDecimal leftAmount3 = BigDecimal.ZERO;

    private String user4Id;

    private String user4Name;

    private BigDecimal percent4 = BigDecimal.ZERO;

    private BigDecimal profit4 = BigDecimal.ZERO;

    private BigDecimal withdrawAmount4 = BigDecimal.ZERO;

    private BigDecimal leftAmount4 = BigDecimal.ZERO;

    private String user5Id;

    private String user5Name;

    private BigDecimal percent5 = BigDecimal.ZERO;

    private BigDecimal profit5 = BigDecimal.ZERO;

    private BigDecimal withdrawAmount5 = BigDecimal.ZERO;

    private BigDecimal leftAmount5 = BigDecimal.ZERO;

    private BigDecimal totalProfit = BigDecimal.ZERO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public ProjectSettlement.ProjectSettlementStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectSettlement.ProjectSettlementStatus status) {
        this.status = status;
    }

    public BigDecimal getReceivedPaymentAmount() {
        return receivedPaymentAmount;
    }

    public void setReceivedPaymentAmount(BigDecimal receivedPaymentAmount) {
        this.receivedPaymentAmount = receivedPaymentAmount;
    }

    public String getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(String user1Id) {
        this.user1Id = user1Id;
    }

    public String getUser1Name() {
        return user1Name;
    }

    public void setUser1Name(String user1Name) {
        this.user1Name = user1Name;
    }

    public BigDecimal getPercent1() {
        return percent1;
    }

    public void setPercent1(BigDecimal percent1) {
        this.percent1 = percent1;
    }

    public BigDecimal getProfit1() {
        return profit1;
    }

    public void setProfit1(BigDecimal profit1) {
        this.profit1 = profit1;
    }

    public BigDecimal getMarketPercent1() {
        return marketPercent1;
    }

    public void setMarketPercent1(BigDecimal marketPercent1) {
        this.marketPercent1 = marketPercent1;
    }

    public BigDecimal getMarketProfit1() {
        return marketProfit1;
    }

    public void setMarketProfit1(BigDecimal marketProfit1) {
        this.marketProfit1 = marketProfit1;
    }

    public BigDecimal getWithdrawAmount1() {
        return withdrawAmount1;
    }

    public void setWithdrawAmount1(BigDecimal withdrawAmount1) {
        this.withdrawAmount1 = withdrawAmount1;
    }

    public BigDecimal getLeftAmount1() {
        return leftAmount1;
    }

    public void setLeftAmount1(BigDecimal leftAmount1) {
        this.leftAmount1 = leftAmount1;
    }

    public String getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(String user2Id) {
        this.user2Id = user2Id;
    }

    public String getUser2Name() {
        return user2Name;
    }

    public void setUser2Name(String user2Name) {
        this.user2Name = user2Name;
    }

    public BigDecimal getPercent2() {
        return percent2;
    }

    public void setPercent2(BigDecimal percent2) {
        this.percent2 = percent2;
    }

    public BigDecimal getProfit2() {
        return profit2;
    }

    public void setProfit2(BigDecimal profit2) {
        this.profit2 = profit2;
    }

    public BigDecimal getMarketPercent2() {
        return marketPercent2;
    }

    public void setMarketPercent2(BigDecimal marketPercent2) {
        this.marketPercent2 = marketPercent2;
    }

    public BigDecimal getMarketProfit2() {
        return marketProfit2;
    }

    public void setMarketProfit2(BigDecimal marketProfit2) {
        this.marketProfit2 = marketProfit2;
    }

    public BigDecimal getWithdrawAmount2() {
        return withdrawAmount2;
    }

    public void setWithdrawAmount2(BigDecimal withdrawAmount2) {
        this.withdrawAmount2 = withdrawAmount2;
    }

    public BigDecimal getLeftAmount2() {
        return leftAmount2;
    }

    public void setLeftAmount2(BigDecimal leftAmount2) {
        this.leftAmount2 = leftAmount2;
    }

    public String getUser3Id() {
        return user3Id;
    }

    public void setUser3Id(String user3Id) {
        this.user3Id = user3Id;
    }

    public String getUser3Name() {
        return user3Name;
    }

    public void setUser3Name(String user3Name) {
        this.user3Name = user3Name;
    }

    public BigDecimal getPercent3() {
        return percent3;
    }

    public void setPercent3(BigDecimal percent3) {
        this.percent3 = percent3;
    }

    public BigDecimal getProfit3() {
        return profit3;
    }

    public void setProfit3(BigDecimal profit3) {
        this.profit3 = profit3;
    }

    public BigDecimal getMarketPercent3() {
        return marketPercent3;
    }

    public void setMarketPercent3(BigDecimal marketPercent3) {
        this.marketPercent3 = marketPercent3;
    }

    public BigDecimal getMarketProfit3() {
        return marketProfit3;
    }

    public void setMarketProfit3(BigDecimal marketProfit3) {
        this.marketProfit3 = marketProfit3;
    }

    public BigDecimal getWithdrawAmount3() {
        return withdrawAmount3;
    }

    public void setWithdrawAmount3(BigDecimal withdrawAmount3) {
        this.withdrawAmount3 = withdrawAmount3;
    }

    public BigDecimal getLeftAmount3() {
        return leftAmount3;
    }

    public void setLeftAmount3(BigDecimal leftAmount3) {
        this.leftAmount3 = leftAmount3;
    }

    public String getUser4Id() {
        return user4Id;
    }

    public void setUser4Id(String user4Id) {
        this.user4Id = user4Id;
    }

    public String getUser4Name() {
        return user4Name;
    }

    public void setUser4Name(String user4Name) {
        this.user4Name = user4Name;
    }

    public BigDecimal getPercent4() {
        return percent4;
    }

    public void setPercent4(BigDecimal percent4) {
        this.percent4 = percent4;
    }

    public BigDecimal getProfit4() {
        return profit4;
    }

    public void setProfit4(BigDecimal profit4) {
        this.profit4 = profit4;
    }

    public BigDecimal getWithdrawAmount4() {
        return withdrawAmount4;
    }

    public void setWithdrawAmount4(BigDecimal withdrawAmount4) {
        this.withdrawAmount4 = withdrawAmount4;
    }

    public BigDecimal getLeftAmount4() {
        return leftAmount4;
    }

    public void setLeftAmount4(BigDecimal leftAmount4) {
        this.leftAmount4 = leftAmount4;
    }

    public String getUser5Id() {
        return user5Id;
    }

    public void setUser5Id(String user5Id) {
        this.user5Id = user5Id;
    }

    public String getUser5Name() {
        return user5Name;
    }

    public void setUser5Name(String user5Name) {
        this.user5Name = user5Name;
    }

    public BigDecimal getPercent5() {
        return percent5;
    }

    public void setPercent5(BigDecimal percent5) {
        this.percent5 = percent5;
    }

    public BigDecimal getProfit5() {
        return profit5;
    }

    public void setProfit5(BigDecimal profit5) {
        this.profit5 = profit5;
    }

    public BigDecimal getWithdrawAmount5() {
        return withdrawAmount5;
    }

    public void setWithdrawAmount5(BigDecimal withdrawAmount5) {
        this.withdrawAmount5 = withdrawAmount5;
    }

    public BigDecimal getLeftAmount5() {
        return leftAmount5;
    }

    public void setLeftAmount5(BigDecimal leftAmount5) {
        this.leftAmount5 = leftAmount5;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

}
