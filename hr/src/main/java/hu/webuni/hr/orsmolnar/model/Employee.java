package hu.webuni.hr.orsmolnar.model;

import java.time.LocalDateTime;

public class Employee {

	private long id;
	private String name;
	private int salary;
	private LocalDateTime entryDate;
	
	public Employee(long id, String name, int salary, LocalDateTime entryDate) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.entryDate = entryDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public LocalDateTime getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}
}
