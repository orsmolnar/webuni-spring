package hu.webuni.hr.orsmolnar.service;

import java.util.List;
import java.util.Optional;

import hu.webuni.hr.orsmolnar.model.Employee;

public interface EmployeeService {
	
	public double getPayRaisePercent(Employee employee);
	
	public List<Employee> findAll();
	
//	public List<Employee> findAllByLimit(int limit);
	
	public Optional<Employee> findById(long id);
	
	public Employee save(Employee employee);
	
	public Employee update(Employee employee);
	
	public void delete(long id);
}
