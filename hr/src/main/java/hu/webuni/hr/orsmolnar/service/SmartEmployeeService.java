package hu.webuni.hr.orsmolnar.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.orsmolnar.config.HrConfigProperties;
import hu.webuni.hr.orsmolnar.config.HrConfigProperties.Payraise.Range;
import hu.webuni.hr.orsmolnar.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService{
	
	@Autowired
	HrConfigProperties configProperties;
	
	
	@Override
	public double getPayRaisePercent(Employee employee) {
		
		LocalDate currentDate = LocalDate.now();
		long employeeNumOfDays = ChronoUnit.DAYS.between(employee.getEntryDate(), currentDate);
		
		List<Range> ranges = configProperties.getPayraise().getSmartRanges();	
		
		Comparator<Range> compareByTerm = Comparator.comparingDouble(Range::getTermInYears);
		List<Range> sortedRanges = ranges.stream()
											.sorted(compareByTerm.reversed())
											.collect(Collectors.toList());
		
		for (Range range : sortedRanges ) {
			if (employeeNumOfDays >= range.getTermInYears()*365) {
				return range.getPercent();
			}
		}	
		return 0;
	}
}
