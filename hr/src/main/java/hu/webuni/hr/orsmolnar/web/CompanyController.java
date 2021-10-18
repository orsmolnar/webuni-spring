package hu.webuni.hr.orsmolnar.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.orsmolnar.dto.CompanyDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

	private Map<Long, CompanyDto> companyDtos = new HashMap<>();
	
	{
		companyDtos.put(1L, new CompanyDto(1L, "01-01-123456", "myFirstCompany", "address1"));
		companyDtos.put(2L, new CompanyDto(2L, "02-02-123456", "mySecondCompany", "address2"));
		companyDtos.put(3L, new CompanyDto(3L, "03-03-123456", "myThirdCompany", "address3"));
	}
	
	
	@GetMapping
	public List<CompanyDto> getAllCompanies() {
		return new ArrayList<>(companyDtos.values());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompanyDto> getCompanyById(@PathVariable long id) {
		CompanyDto companyDto = companyDtos.get(id);
		if (companyDto != null) {
			return ResponseEntity.ok(companyDto); 
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public CompanyDto addCompany(@RequestBody CompanyDto companyDto) {
		companyDtos.put(companyDto.getCompanyId(), companyDto);
		return companyDto;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		if (companyDtos.containsKey(id)) {
			companyDto.setCompanyId(id);
			companyDtos.put(id, companyDto);
			return ResponseEntity.ok(companyDto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CompanyDto> deleteCompany(@PathVariable long id) {
		CompanyDto companyDtoToDelete = companyDtos.get(id);
		if (companyDtoToDelete != null) {
			companyDtos.remove(id);
			return ResponseEntity.ok(companyDtoToDelete);
		}
		return ResponseEntity.notFound().build();
	}
}
