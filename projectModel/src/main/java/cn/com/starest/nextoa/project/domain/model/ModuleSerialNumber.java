package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The module feature name as the id
 *
 * @author dz
 */
@Document(collection = "ModuleSerialNumbers")
public class ModuleSerialNumber extends StringIdentifier {

	private String date;

	private int number;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
