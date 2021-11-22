package hu.webuni.hr.orsmolnar.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.orsmolnar.dto.CompanyDto;
import hu.webuni.hr.orsmolnar.dto.EmployeeDto;
import hu.webuni.hr.orsmolnar.model.Company;
import hu.webuni.hr.orsmolnar.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

//	ezt akkor kellene, ha az entity és a dto mezői nem egyeznek
//	@Mapping(target = "id", source = "employeeId")
//  @Mapping(target = "company.employees", ignore = true)	- ez a 2. megoldás a kerszthivatkozás megakadályozására
	@Mapping(target = "title", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);
	
	
	//a keresztbehivatkozást így akadályozzuk meg - az employee-kat ne rakja bele a companyDto-ba
	//1. megoldás
	//ha az employee-ban talál egy company-t, akkor ez a comany mapper metódust fogja hívni rá
	@Mapping(target = "employees", ignore = true)
	CompanyDto companyToDto(Company c);

	
//	ezt akkor kellene, ha az entity és a dto mezői nem egyeznek
//	@InheritInverseConfiguration - és akkor nem kell újra felsorolni ugyanazt mint fent fordítva
	Employee dtoToEmployee(EmployeeDto employeeDto);

	
	List<EmployeeDto> employeesToDtos (List<Employee> employees);

	List<Employee> dtosToEmployees(List<EmployeeDto> employeeDtos);

}
