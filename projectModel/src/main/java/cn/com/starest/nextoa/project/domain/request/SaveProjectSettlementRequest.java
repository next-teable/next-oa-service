package cn.com.starest.nextoa.project.domain.request;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface SaveProjectSettlementRequest {

	/**
	 * @return
	 */
	String getUser1Id();

	/**
	 * @return
	 */
	BigDecimal getPercent1();

	/**
	 * @return
	 */
	BigDecimal getMarketPercent1();

	/**
	 * @return
	 */
	String getUser2Id();

	/**
	 * @return
	 */
	BigDecimal getPercent2();

	/**
	 * @return
	 */
	BigDecimal getMarketPercent2();

	/**
	 * @return
	 */
	String getUser3Id();

	/**
	 * @return
	 */
	BigDecimal getPercent3();

	/**
	 * @return
	 */
	BigDecimal getMarketPercent3();

	/**
	 * @return
	 */
	String getUser4Id();

	/**
	 * @return
	 */
	BigDecimal getPercent4();

	/**
	 * @return
	 */
	String getUser5Id();

	/**
	 * @return
	 */
	BigDecimal getPercent5();

}
