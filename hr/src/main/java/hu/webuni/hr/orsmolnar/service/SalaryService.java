package hu.webuni.hr.orsmolnar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.orsmolnar.model.Employee;

@Service
public class SalaryService {

	private EmployeeService employeeService;

	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public void setNewSalary(Employee employee) {
		employee.setSalary((int) Math.round(employee.getSalary()*(employeeService.getPayRaisePercent(employee)*0.01 + 1)));
	}
}
