package hu.webuni.hr.orsmolnar.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.webuni.hr.orsmolnar.dto.CompanyDto;
import hu.webuni.hr.orsmolnar.dto.EmployeeDto;
import hu.webuni.hr.orsmolnar.model.Company;
import hu.webuni.hr.orsmolnar.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	CompanyDto companyToDto(Company company);
	
	Company dtoToCompany(CompanyDto companyDto);
	
	List<CompanyDto> companiesToDtos(List<Company> companies);
	
	List<Company> dtosToCompanies(List<CompanyDto> companyDtos);

	
	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companyToDtoWithoutEmployees(Company company);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToDtosWithoutEmployees(List<Company> companies);
	
	//Ezeket áthoztuk az employeeMapperből, mert a company-nak is vannak employee-jai, a mapping kapcsolatokat itt is meg kell adni
//	ezt akkor kellene, ha az entity és a dto mezői nem egyeznek
//	@Mapping(target = "id", source = "employeeId")
	@Mapping(target = "title", source = "position.name")
	EmployeeDto employeeToDto(Employee employee);

//	ezt akkor kellene, ha az entity és a dto mezői nem egyeznek
//	@InheritInverseConfiguration - és akkor nem kell újra felsorolni ugyanazt mint fent fordítva
	Employee dtoToEmployee(EmployeeDto employeeDto);

}
