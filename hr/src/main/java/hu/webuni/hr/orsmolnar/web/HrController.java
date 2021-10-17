package hu.webuni.hr.orsmolnar.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.orsmolnar.dto.EmployeeDto;

@RestController
@RequestMapping("/api/employees")
public class HrController {

	private Map<Long, EmployeeDto> employeeDtos = new HashMap<>();
	
	{
		employeeDtos.put(1L, new EmployeeDto(1L, "Bill", "developer", 1000, LocalDate.parse("2011-05-01")));
		employeeDtos.put(2L, new EmployeeDto(2L, "Peter", "tester", 1100, LocalDate.parse("2016-05-01")));
		employeeDtos.put(3L, new EmployeeDto(3L, "Tom", "scrum master", 1200, LocalDate.parse("2018-09-01")));
	}
	
	@GetMapping
	public List<EmployeeDto> getAllEmployees() {
		return new ArrayList<>(employeeDtos.values());	
	}
	
	//az url-ben meg kell adni a paramétert, hogy tudja melyik GET metódust kell meghívnia (az "/api/employees"-re megy a getAllEmployees() is!!  
	@GetMapping(params = "limit")
	public List<EmployeeDto> getEmployeesOverSalaryLimit(@RequestParam int limit) {
		List<EmployeeDto> employeeDtoList = new ArrayList<>(employeeDtos.values());
		return employeeDtoList.stream()
			    			  .filter(e -> e.getEmployeeSalary() > limit)
			    			  .collect(Collectors.toList());
	}
	
//	//getAllEmployees() és getEmployeesOverSalaryLimit() egy metódusban kezelve, opcionális request paraméterrel
//	@GetMapping
//	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer limit) {
//		return limit == null ?
//				new ArrayList<>(employeeDtos.values()):
//					employeeDtos.values().stream()
//										 .filter(e -> e.getEmployeeSalary() > limit)
//										 .collect(Collectors.toList());
//	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id) {
		EmployeeDto employeeDto = employeeDtos.get(id);
		if (employeeDto != null) {
			return ResponseEntity.ok(employeeDto); 
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
		employeeDtos.put(employeeDto.getEmployeeId(), employeeDto);
		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		if (employeeDtos.containsKey(id)) {
			employeeDto.setEmployeeId(id);
			employeeDtos.put(id, employeeDto);
			return ResponseEntity.ok(employeeDto);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable long id) {
		EmployeeDto employeeDtoToDelete = employeeDtos.get(id);
		if (employeeDtoToDelete != null) {
			employeeDtos.remove(id);
			return ResponseEntity.ok(employeeDtoToDelete);
		}
		return ResponseEntity.notFound().build();
	}
}
