package hu.webuni.hr.orsmolnar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.orsmolnar.dto.EmployeeDto;
import hu.webuni.hr.orsmolnar.model.Employee;
import hu.webuni.hr.orsmolnar.repository.EmployeeRepository;


//ő nem bean csak a gyerekei - abstract, úgyse tud példányosulni
public abstract class AbstractEmployeeService implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
//	public List<Employee> findAllByLimit(int limit) {
//		return employees.values().stream()
//				   				 .filter(e -> e.getSalary() > limit)
//				   				 .collect(Collectors.toList());
//	}

	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	@Transactional
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Transactional
	public Employee update(Employee employee) {
		if (employee.getId() == null || !employeeRepository.existsById(employee.getId())) {
			return null;
		}
		return employeeRepository.save(employee);	
	}

	@Transactional
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}	
	
	
}
