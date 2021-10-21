package hu.webuni.hr.orsmolnar.dto;

import java.util.List;

public class CompanyDto {
	private long id;
	private String regNum;
	private String name;
	private String address;
	private List<EmployeeDto> employees;
	
	public CompanyDto() {}
	
	public CompanyDto(long id, String regNum, String name, String address) {
		this.id = id;
		this.regNum = regNum;
		this.name = name;
		this.address = address;
	}
	
	public CompanyDto(long id, String regNum, String name, String address, List<EmployeeDto> employees) {
		this.id = id;
		this.regNum = regNum;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<EmployeeDto> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
}
