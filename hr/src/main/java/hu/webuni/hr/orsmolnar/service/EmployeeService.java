package hu.webuni.hr.orsmolnar.service;

import java.util.List;

import hu.webuni.hr.orsmolnar.model.Employee;

public interface EmployeeService {
	
	public double getPayRaisePercent(Employee employee);
	
	public List<Employee> findAll();
	
	public List<Employee> findAllByLimit(int limit);
	
	public Employee findById(long id);
	
	public void create(Employee employee);
	
	public void update(long id, Employee employee);
	
	public void delete(long id);
}
