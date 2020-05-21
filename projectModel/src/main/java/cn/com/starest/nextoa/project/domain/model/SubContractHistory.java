package cn.com.starest.nextoa.project.domain.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 分包合同修改历史记录
 *
 * @author dz
 */
@Document(collection = "SubContractHistories")
public class SubContractHistory extends AbstractSubContract {

	@Indexed
	@DBRef(lazy = true)
	private SubContract subContract;

	public SubContract getSubContract() {
		return subContract;
	}

	public void setSubContract(SubContract subContract) {
		this.subContract = subContract;
	}
}
