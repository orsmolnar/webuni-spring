package hu.webuni.hr.orsmolnar.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class EmployeeDto {
	
	private long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String title;
	
	@Min(1)
	private int salary;
	
	@Past
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

	@Override
	public int hashCode() {
		return Objects.hash(entryDate, id, name, salary, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDto other = (EmployeeDto) obj;
		return Objects.equals(entryDate, other.entryDate) && id == other.id && Objects.equals(name, other.name)
				&& salary == other.salary && Objects.equals(title, other.title);
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		EmployeeDto other = (EmployeeDto) obj;
//		return id == other.id;
//	}
	
	
}
