package hu.webuni.hr.orsmolnar.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.orsmolnar.dto.EmployeeDto;
import hu.webuni.hr.orsmolnar.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

//	ezt akkor kellene, ha az entity és a dto mezői nem egyeznek
//	@Mapping(target = "id", source = "employeeId")
	EmployeeDto employeeToDto(Employee employee);

//	ezt akkor kellene, ha az entity és a dto mezői nem egyeznek
//	@InheritInverseConfiguration - és akkor nem kell újra felsorolni ugyanazt mint fent fordítva
	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	List<EmployeeDto> employeesToDtos (List<Employee> employees);

	List<Employee> dtosToEmployees(List<EmployeeDto> employeeDtos);

}
