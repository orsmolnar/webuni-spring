package hu.webuni.hr.orsmolnar.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.webuni.hr.orsmolnar.config.HrConfigProperties;
import hu.webuni.hr.orsmolnar.config.HrConfigProperties.Payraise.Range;
import hu.webuni.hr.orsmolnar.model.Employee;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {
	
	@Autowired
	HrConfigProperties configProperties;
	
	
	@Override
	public double getPayRaisePercent(Employee employee) {
		
//		long employeeNumOfDays = ChronoUnit.DAYS.between(employee.getEntryDate(), LocalDate.now());
		
		Period period = Period.between(employee.getEntryDate(), LocalDate.now());
		int employeeNumOfMonths = period.getYears()*12 + period.getMonths();
		 
		List<Range> ranges = configProperties.getPayraise().getSmartRanges();	
		
		Comparator<Range> compareByTerm = Comparator.comparing(Range::getYearsOfTerm)
													.thenComparing(Range::getMonthsOfTerm);
		List<Range> sortedRanges = ranges.stream()
										  .sorted(compareByTerm.reversed())
										  .collect(Collectors.toList());
		
		for (Range range : sortedRanges ) {
			if (employeeNumOfMonths >= range.getYearsOfTerm()*12 + range.getMonthsOfTerm()) {
				return range.getPercent();
			}
		}	
		return 0;
	}

}
