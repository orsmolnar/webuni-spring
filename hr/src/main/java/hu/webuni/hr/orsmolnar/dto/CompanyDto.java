package hu.webuni.hr.orsmolnar.dto;

public class CompanyDto {
	private long companyId;
	private String companyRegNum;
	private String companyName;
	private String companyAddress;
	
	public CompanyDto() {}
	
	public CompanyDto(long companyId, String companyRegNum, String companyName, String companyAddress) {
		this.companyId = companyId;
		this.companyRegNum = companyRegNum;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
	}

	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyRegNum() {
		return companyRegNum;
	}
	public void setCompanyRegNum(String companyRegNum) {
		this.companyRegNum = companyRegNum;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
}
