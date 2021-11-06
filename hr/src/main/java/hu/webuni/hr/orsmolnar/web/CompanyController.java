package hu.webuni.hr.orsmolnar.web;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.orsmolnar.dto.CompanyDto;
import hu.webuni.hr.orsmolnar.dto.EmployeeDto;
import hu.webuni.hr.orsmolnar.mapper.CompanyMapper;
import hu.webuni.hr.orsmolnar.mapper.EmployeeMapper;
import hu.webuni.hr.orsmolnar.model.Company;
import hu.webuni.hr.orsmolnar.model.Employee;
import hu.webuni.hr.orsmolnar.service.CompanyService;


@RestController
@RequestMapping("/api/companies")
public class CompanyController{
	
	@Autowired   
	CompanyService companyService;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	
	@GetMapping
	public List<CompanyDto> getAllCompanies(@RequestParam(required = false) boolean full) {
		if (full == true) {
			return new ArrayList<>(companyMapper.companiesToDtos(companyService.findAll()));
		} else {
			return companyMapper.companiesToDtos(companyService.findAll()).stream()
																		  .map(c -> new CompanyDto(c.getId(), 
																   								   c.getRegNum(), 
																   								   c.getName(), 
																   								   c.getAddress()))
																		  .collect(Collectors.toList());
		}
	}
	
	
	@GetMapping("/{cid}")
	public CompanyDto getCompanyById(@PathVariable long cid, @RequestParam(required = false) boolean full) {
		Company company = companyService.findById(cid);
		if (company != null) {
			if (full == true) {
				return companyMapper.companyToDto(company); 
			} else {
				return companyMapper.companyToDto(
						new Company(company.getId(), company.getRegNum(), company.getName(), company.getAddress()));
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	public CompanyDto addCompany(@RequestBody CompanyDto companyDto) {
		Company company = companyService.save(companyMapper.dtoToCompany(companyDto));  
		return companyMapper.companyToDto(company);
	}
	
	
	@PostMapping("/{cid}/employees")
	public CompanyDto addEmployeeToCompany(@PathVariable long cid, @RequestBody EmployeeDto employeeDto) {
		Company company = companyService.findById(cid);
		if (company != null) {
			company = companyService.addEmployee(company, employeeMapper.dtoToEmployee(employeeDto));
			return companyMapper.companyToDto(company);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
 
	
	@PutMapping("/{cid}")
	public CompanyDto modifyCompany(@PathVariable long cid, @RequestBody CompanyDto companyDto) {
		Company companyToModify = companyService.findById(cid);
		if(companyToModify != null) {
			companyToModify = companyService.modify(companyToModify, companyMapper.dtoToCompany(companyDto));
			return companyMapper.companyToDto(companyToModify);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/{cid}/employees")
	public CompanyDto changeAllEmployeesInCompany(@PathVariable long cid, @RequestBody List<EmployeeDto> newEmployees) {
		Company company = companyService.findById(cid);
		if (company != null) {
			List<Employee> newEmployeeList = employeeMapper.dtosToEmployees(newEmployees);
			company = companyService.changeEmployees(company, newEmployeeList);
			return companyMapper.companyToDto(company);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{cid}")
	public CompanyDto deleteCompany(@PathVariable long cid) {
		Company companyToDelete = companyService.findById(cid);
		if (companyToDelete != null) {
			companyService.delete(cid);
			return companyMapper.companyToDto(companyToDelete);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{cid}/employees")
	public CompanyDto deleteAllEmployeesInCompany(@PathVariable long cid) {
		Company company = companyService.findById(cid);
		if (company != null) {
			companyService.deleteEmployees(company);
			return companyMapper.companyToDto(company);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{cid}/employees/{eid}")
	public CompanyDto deleteEmployeeInCompany(@PathVariable long cid, @PathVariable long eid) {
		Company companyFromDelete = companyService.findById(cid);
		if (companyFromDelete != null && companyFromDelete.getEmployees() != null) {
			try {
				companyService.deleteEmployee(companyFromDelete, eid);
			} catch (NoSuchElementException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			return companyMapper.companyToDto(companyFromDelete);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND); 

	}
}
