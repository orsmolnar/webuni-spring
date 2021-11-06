package hu.webuni.hr.orsmolnar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.expression.EvaluationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import hu.webuni.hr.orsmolnar.dto.EmployeeDto;
import hu.webuni.hr.orsmolnar.model.Employee;


@Service
public abstract class AbstractEmployeeService implements EmployeeService{
	
private Map<Long, Employee> employees = new HashMap<>();
	
	{
		employees.put(1L, new Employee(1L, "Bill", "developer", 1200, LocalDate.parse("2011-05-01")));
		employees.put(2L, new Employee(2L, "Peter", "tester", 1100, LocalDate.parse("2016-05-01")));
		employees.put(3L, new Employee(3L, "Tom", "scrum master", 1150, LocalDate.parse("2018-09-01")));
		employees.put(4L, new Employee(4L, "James", "developer", 1200, LocalDate.parse("2021-06-01")));
		employees.put(5L, new Employee(5L, "Mark", "product owner", 1300, LocalDate.parse("2019-09-01")));
	}
	
	public abstract double getPayRaisePercent(Employee employee);

	public List<Employee> findAll() {
		return new ArrayList<>(employees.values());
	}
	
	public List<Employee> findAllByLimit(int limit) {
		return employees.values().stream()
				   				 .filter(e -> e.getSalary() > limit)
				   				 .collect(Collectors.toList());
	}

	public Employee findById(long id) {
		return employees.get(id);
	}

	public void create(Employee employee) throws EvaluationException{
		employees.put(employee.getId(), employee);
	}

	public void update(long id, Employee employee){
			employees.replace(id, employee);	
	}

	public void delete(long id) {
		employees.remove(id);
		System.out.println(employees);
	}
	
	
	
}
