package hu.webuni.hr.orsmolnar.dto;

import java.time.LocalDate;

public class EmployeeDto {
	private long employeeId;
	private String employeeName;
	private String employeeJobTitle;
	private int employeeSalary;
	private LocalDate employeeEntryDate;
	
	public EmployeeDto() {}
	
	public EmployeeDto(long employeeId, String employeeName, String employeeJobTitle, int employeeSalary,
			LocalDate employeeEntryDate) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeJobTitle = employeeJobTitle;
		this.employeeSalary = employeeSalary;
		this.employeeEntryDate = employeeEntryDate;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeJobTitle() {
		return employeeJobTitle;
	}
	public void setEmployeeJobTitle(String employeeJobTitle) {
		this.employeeJobTitle = employeeJobTitle;
	}
	public int getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(int employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	public LocalDate getEmployeeEntryDate() {
		return employeeEntryDate;
	}
	public void setEmployeeEntryDate(LocalDate employeeEntryDate) {
		this.employeeEntryDate = employeeEntryDate;
	}
}
