package hu.webuni.hr.orsmolnar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.orsmolnar.config.HrConfigProperties;
import hu.webuni.hr.orsmolnar.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService{

	@Autowired
	HrConfigProperties configProperties;
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		
		return configProperties.getPayraise().getDefaultPercent();
	}
}