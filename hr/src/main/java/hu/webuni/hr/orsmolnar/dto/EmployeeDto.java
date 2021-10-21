package hu.webuni.hr.orsmolnar.dto;

import java.time.LocalDate;

public class EmployeeDto {
	private long id;
	private String name;
	private String title;
	private int salary;
	private LocalDate entryDate;
	
	public EmployeeDto() {}
	
	public EmployeeDto(long id, String name, String title, int salary, LocalDate entryDate) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.salary = salary;
		this.entryDate = entryDate;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public LocalDate getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
}
