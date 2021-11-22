package hu.webuni.hr.orsmolnar.web;

import java.util.List;
import java.util.NoSuchElementException;

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
import hu.webuni.hr.orsmolnar.model.AverageSalaryByPosition;
import hu.webuni.hr.orsmolnar.model.Company;
import hu.webuni.hr.orsmolnar.repository.CompanyRepository;
import hu.webuni.hr.orsmolnar.service.CompanyService;


@RestController
@RequestMapping("/api/companies")
public class CompanyController{
   
	private CompanyService companyService;
	private CompanyMapper companyMapper;
	private EmployeeMapper employeeMapper;
	private CompanyRepository companyRepository;
	
	public CompanyController(CompanyService companyService, CompanyMapper companyMapper, 
							 EmployeeMapper employeeMapper, CompanyRepository companyRepository) {
		super();
		this.companyService = companyService;
		this.companyMapper = companyMapper;
		this.employeeMapper = employeeMapper;
		this.companyRepository = companyRepository;
	}


	@GetMapping
	public List<CompanyDto> getAllCompanies(@RequestParam(required = false) boolean full) {
		List<Company> companies = companyService.findAll();
		return mapCompanies(companies, full);
	}
	
	private List<CompanyDto> mapCompanies(List<Company> allCompanies, boolean full) {
		return full ? companyMapper.companiesToDtos(allCompanies) 
				: companyMapper.companiesToDtosWithoutEmployees(allCompanies);
	}
	
	
	@GetMapping("/{cid}")
	public CompanyDto getCompanyById(@PathVariable long cid, @RequestParam(required = false) boolean full) {
		
		//idióma kontrollerekben: ha Optional-t ad vissza, akkor .orElseThrow()-val lekezeljük a hibát is
		Company company = companyService.findById(cid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return full ? companyMapper.companyToDto(company) 
				: companyMapper.companyToDtoWithoutEmployees(company);
	}
	
	
	@PostMapping
	public CompanyDto createCompany(@RequestBody CompanyDto companyDto) {
		Company company = companyService.save(companyMapper.dtoToCompany(companyDto));  
		return companyMapper.companyToDto(company);
	}
	
	
	@PostMapping("/{cid}/employees")
	public CompanyDto addNewEmployeeToCompany(@PathVariable long cid, @RequestBody EmployeeDto employeeDto) {
		try {
			Company company = companyService.addEmployee(cid, employeeMapper.dtoToEmployee(employeeDto));
			return companyMapper.companyToDto(company);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
 
	
	@PutMapping("/{cid}")
	public CompanyDto modifyCompany(@PathVariable long cid, @RequestBody CompanyDto companyDto) {
		Company company = companyMapper.dtoToCompany(companyDto);
		company.setId(cid);
		return companyMapper.companyToDto(companyService.update(company));
	}
	
	
	@PutMapping("/{cid}/employees")
	public CompanyDto changeAllEmployeesInCompany(@PathVariable long cid, @RequestBody List<EmployeeDto> newEmployees) {
		Company company = companyService.replaceEmployees(cid, employeeMapper.dtosToEmployees(newEmployees));
		return companyMapper.companyToDto(company);
	}
	
	@DeleteMapping("/{cid}")
	public void deleteCompany(@PathVariable long cid) {
		companyService.delete(cid);
	}
	
	@DeleteMapping("/{cid}/employees/{eid}")
	public CompanyDto deleteEmployeeInCompany(@PathVariable long cid, @PathVariable long eid) {
		return companyMapper.companyToDto(companyService.deleteEmployee(cid, eid));
	}
	
	@DeleteMapping("/{cid}/employees")
	public CompanyDto deleteAllEmployeesInCompany(@PathVariable long cid) {
		return changeAllEmployeesInCompany(cid, null);
	}
	
	
	@GetMapping(params = "aboveSalary")
	public List<CompanyDto> getCompaniesAboveSalary(@RequestParam int aboveSalary, 
													@RequestParam(required = false) boolean full) {
		List<Company> companies = companyRepository.findByEmployeeWithSalaryHigherThan(aboveSalary);
		return mapCompanies(companies, full);
	}
	
	@GetMapping(params = "aboveEmployeeNumber")
	public List<CompanyDto> getCompaniesAboveEmployeeNumber(@RequestParam int aboveEmployeeNumber, 
															@RequestParam(required = false) boolean full) {
		List<Company> companies = companyRepository.findByEmployeeCountHigherThan(aboveEmployeeNumber);
		return mapCompanies(companies, full);
	}
	
	@GetMapping("/{id}/salaryStats")
	public List<AverageSalaryByPosition> getSalaryStatsById(@PathVariable long id, 
															@RequestParam(required = false) boolean full) {
		return companyRepository.findAverageSalariesByPosition(id);
	}
}











