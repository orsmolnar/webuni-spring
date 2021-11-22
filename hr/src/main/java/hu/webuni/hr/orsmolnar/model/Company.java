package hu.webuni.hr.orsmolnar.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Company {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String regNum;
	private String name;
	private String address;
	
	@OneToMany(mappedBy = "company")
	private List<Employee> employees;
	
	@ManyToOne
	private CompanyType companyType;
	
	public Company() {}
	
	public Company(Long id, String regNum, String name, String address) {
		this.id = id;
		this.regNum = regNum;
		this.name = name;
		this.address = address;
	}
	
	public Company(Long id, String regNum, String name, String address, List<Employee> employees) {
		this.id = id;
		this.regNum = regNum;
		this.name = name;
		this.address = address;
		this.employees = employees;
	}
	
	
	public void addEmployee(Employee employee) {
		if(this.employees == null) {
			this.employees = new ArrayList<>();
		}
		this.employees.add(employee);
		employee.setCompany(this);
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
