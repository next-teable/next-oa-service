package cn.com.starest.nextoa.project.domain.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 合同修改历史记录
 *
 * @author dz
 */
@Document(collection = "ContractHistories")
public class ContractHistory extends AbstractContract {

	@Indexed
	@DBRef(lazy = true)
	private Contract contract;

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}


}
