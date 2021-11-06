package hu.webuni.hr.orsmolnar.model;

import java.util.List;

public class Company {
	private long id;
	private String regNum;
	private String name;
	private String address;
	private List<Employee> employees;
	
	public Company() {}
	
	public Company(long id, String regNum, String name, String address) {
		this.id = id;
		this.regNum = regNum;
		this.name = name;
		this.address = address;
	}
	
	public Company(long id, String regNum, String name, String address, List<Employee> employees) {
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
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
