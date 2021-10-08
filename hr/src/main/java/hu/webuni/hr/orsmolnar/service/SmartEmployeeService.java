package hu.webuni.hr.orsmolnar.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.orsmolnar.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService{
	
	private LocalDate currentDate = LocalDate.now();
	
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		
		Period period = Period.between(employee.getEntryDate().toLocalDate(), currentDate);
		
		if (period.getYears() >= 10) {
			return 10;
		}
		if (period.getYears() >= 5) {
			return 5;
		}
		if (period.getYears() >= 3 || (period.getYears() == 2 && period.getMonths() >= 6)) {
			return 2;
		}	
		return 0;
	}

}
