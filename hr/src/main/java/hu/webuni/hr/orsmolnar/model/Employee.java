package hu.webuni.hr.orsmolnar.model;

import java.time.LocalDate;

public class Employee {

	private long id;
	private String name;
	private int salary;
	private LocalDate entryDate;
	
	public Employee(long id, String name, int salary, LocalDate entryDate) {
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
	public LocalDate getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
}
