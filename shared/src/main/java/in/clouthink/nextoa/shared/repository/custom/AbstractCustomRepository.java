package in.clouthink.nextoa.shared.repository.custom;

import org.springframework.data.repository.NoRepositoryBean;

import java.math.BigDecimal;

@NoRepositoryBean
public interface AbstractCustomRepository {

	class GroupedAggregationAmount {

		private String groupBy;

		private BigDecimal amount = BigDecimal.ZERO;

		public String getGroupBy() {
			return groupBy;
		}

		public void setGroupBy(String groupBy) {
			this.groupBy = groupBy;
		}

		public BigDecimal getAmount() {
			if (amount == null) {
				return amount;
			}
			return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			GroupedAggregationAmount that = (GroupedAggregationAmount) o;

			if (groupBy != null ? !groupBy.equals(that.groupBy) : that.groupBy != null) {
				return false;
			}
			return amount != null ? amount.equals(that.amount) : that.amount == null;
		}

		@Override
		public int hashCode() {
			int result = groupBy != null ? groupBy.hashCode() : 0;
			result = 31 * result + (amount != null ? amount.hashCode() : 0);
			return result;
		}

		@Override
		public String toString() {
			return "GroupedAggregationAmount{" +
				   "groupBy='" + groupBy + '\'' +
				   ", amount=" + amount +
				   '}';
		}
	}

	class AggregationAmount {

		private BigDecimal amount = BigDecimal.ZERO;

		public BigDecimal getAmount() {
			if (amount == null) {
				return amount;
			}
			return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			AggregationAmount that = (AggregationAmount) o;

			return amount != null ? amount.equals(that.amount) : that.amount == null;

		}

		@Override
		public int hashCode() {
			return amount != null ? amount.hashCode() : 0;
		}

		@Override
		public String toString() {
			return "AggregationAmount{" +
				   "amount=" + amount +
				   '}';
		}
	}

}
