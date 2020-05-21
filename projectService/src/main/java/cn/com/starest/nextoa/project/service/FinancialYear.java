package cn.com.starest.nextoa.project.service;

/**
 * @author dz
 */
public class FinancialYear {

	int year;

	boolean current;

	public FinancialYear(int year, boolean current) {
		this.year = year;
		this.current = current;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

}
