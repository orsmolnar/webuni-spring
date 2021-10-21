package hu.webuni.hr.orsmolnar.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.orsmolnar.dto.CompanyDto;
import hu.webuni.hr.orsmolnar.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController{
	
	private List<EmployeeDto> employeesAtFirstCompany = new ArrayList<>();
	{
		employeesAtFirstCompany.add(new EmployeeDto(1L, "Bill", "developer", 1200, LocalDate.parse("2011-05-01")));
		employeesAtFirstCompany.add(new EmployeeDto(2L, "Peter", "tester", 1100, LocalDate.parse("2016-05-01")));
	}
	
	private Map<Long, CompanyDto> companyDtos = new HashMap<>();
	{
		companyDtos.put(1L, new CompanyDto(1L, "01-01-123456", "myFirstCompany", "address1", employeesAtFirstCompany));
		companyDtos.put(2L, new CompanyDto(2L, "02-02-123456", "mySecondCompany", "address2"));
		companyDtos.put(3L, new CompanyDto(3L, "03-03-123456", "myThirdCompany", "address3"));
	}	
	
	
	
	@GetMapping
	public List<CompanyDto> getAllCompanies(@RequestParam(required = false) boolean full) {
		if (full == true) {
			return new ArrayList<>(companyDtos.values());
		} else {
			return companyDtos.values().stream()
					   .map(c -> new CompanyDto(c.getId(), 
							   					c.getRegNum(), 
							   					c.getName(), 
							   					c.getAddress()))
					   .collect(Collectors.toList());
		}
	}
	
	
	@GetMapping("/{cid}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable long cid, @RequestParam(required = false) boolean full) {
		CompanyDto companyDto = companyDtos.get(cid);
		if (companyDto != null) {
			if (full == true) {
				return ResponseEntity.ok(companyDto); 
			} else {
				return ResponseEntity.ok(new CompanyDto(companyDto.getId(), companyDto.getRegNum(), companyDto.getName(), companyDto.getAddress())); 
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	public CompanyDto addCompany(@RequestBody CompanyDto companyDto) {
		companyDtos.put(companyDto.getId(), companyDto);
		return companyDto;
	}
	
	
	@PostMapping("/{cid}/employees")
	public ResponseEntity<CompanyDto> addEmployeeToCompany(@PathVariable long cid, @RequestBody EmployeeDto employeeDto) {
		CompanyDto companyDto = companyDtos.get(cid);
		if (companyDto != null) {
			companyDto.getEmployees().add(employeeDto);
			return ResponseEntity.ok(companyDto);
		}
		return ResponseEntity.notFound().build();
	}
 
	
	@PutMapping("/{cid}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long cid, @RequestBody CompanyDto companyDto) {
		if (companyDtos.containsKey(cid)) {
			companyDto.setId(cid);
			companyDtos.put(cid, companyDto);
			return ResponseEntity.ok(companyDto);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PutMapping("/{cid}/employees")
	public ResponseEntity<CompanyDto> changeAllEmployeesInCompany(@PathVariable long cid, @RequestBody List<EmployeeDto> newEmployees) {
		CompanyDto companyDto = companyDtos.get(cid);
		if (companyDto != null) {
			companyDto.setEmployees(newEmployees);
			return ResponseEntity.ok(companyDto);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<CompanyDto> deleteCompany(@PathVariable long cid) {
		CompanyDto companyDtoToDelete = companyDtos.get(cid);
		if (companyDtoToDelete != null) {
			companyDtos.remove(cid);
			return ResponseEntity.ok(companyDtoToDelete);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{cid}/employees")
	public ResponseEntity<CompanyDto> deleteAllEmployeesInCompany(@PathVariable long cid) {
		CompanyDto companyDto = companyDtos.get(cid);
		if (companyDto != null) {
			companyDto.setEmployees(null);
			return ResponseEntity.ok(companyDto);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{cid}/employees/{eid}")
	public ResponseEntity<CompanyDto> deleteEmployeeInCompany(@PathVariable long cid, @PathVariable long eid) {
		CompanyDto companyDtoFromDelete = companyDtos.get(cid);
		if (companyDtoFromDelete != null && companyDtoFromDelete.getEmployees() != null) {
			List<EmployeeDto> employeeDtoList = companyDtoFromDelete.getEmployees().stream()
																				 .filter(e -> e.getId()== eid)
																				 .collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(employeeDtoList)) {
				companyDtoFromDelete.getEmployees().remove(employeeDtoList.get(0));
				return ResponseEntity.ok(companyDtoFromDelete);
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.notFound().build();
	}
}
