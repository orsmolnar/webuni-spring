package hu.webuni.hr.orsmolnar.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import hu.webuni.hr.orsmolnar.config.HrConfigProperties;
import hu.webuni.hr.orsmolnar.config.HrConfigProperties.Payraise.Range;
import hu.webuni.hr.orsmolnar.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService{
	
	@Autowired
	HrConfigProperties configProperties;
	
	
	@Override
	public int getPayRaisePercent(Employee employee) {
		
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(employee.getEntryDate().toLocalDate(), currentDate);
		int employeeNumOfMonths = period.getYears()*12 + period.getMonths();
		
		List<Range> ranges = configProperties.getPayraise().getSmartRanges();	
		
		Comparator<Range> compareByTerm = Comparator.comparing(Range::getYearsOfTerm).thenComparing(Range::getMonthsOfTerm);
		List<Range> sortedRanges = ranges.stream()
											.sorted(compareByTerm.reversed())
											.collect(Collectors.toList());
		
		
		for (Range range : sortedRanges ) {
			if (employeeNumOfMonths >= (range.getYearsOfTerm()*12 + range.getMonthsOfTerm())) {
				return range.getPercent();
			}
		}
		return 0;
	}
}
