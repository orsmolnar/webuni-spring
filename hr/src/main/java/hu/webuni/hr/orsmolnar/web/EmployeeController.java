package hu.webuni.hr.orsmolnar.web;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import hu.webuni.hr.orsmolnar.dto.EmployeeDto;
import hu.webuni.hr.orsmolnar.mapper.EmployeeMapper;
import hu.webuni.hr.orsmolnar.model.Employee;
import hu.webuni.hr.orsmolnar.repository.EmployeeRepository;
import hu.webuni.hr.orsmolnar.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private EmployeeRepository employeeRepository;

		
//	@GetMapping
//	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary) {
//		List<Employee> employees = null;
//		if (minSalary == null) {
//			employees = employeeService.findAll();
//		} else {
//			employees = employeeRepository.findBySalaryGreaterThan(minSalary);
//		}
//		return employeeMapper.employeesToDtos(employees);
//	} 
	
	//ua mint fent, csak Pageable-vel kiegészítve (a Pageable egy query paraméterként jön by default
	@GetMapping
	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer minSalary, Pageable page) {
		List<Employee> employees = null;
		if (minSalary == null) {
			employees = employeeService.findAll();
		} else {
			Page<Employee> empPage = employeeRepository.findBySalaryGreaterThan(minSalary, page);
			System.out.println(empPage.getNumber());
			System.out.println(empPage.isLast());
			System.out.println(empPage.isFirst());
			System.out.println(empPage.getSize());
			System.out.println(empPage.getTotalElements());
			System.out.println(empPage.getTotalPages());
			employees = empPage.getContent();
		}
		return employeeMapper.employeesToDtos(employees);
	} 
	
	@GetMapping
	public List<EmployeeDto> getEmployeesByTitle(@RequestParam(required = true) String title) {
		List<Employee> employees = null;
		employees = employeeRepository.findByPositionName(title);
		return employeeMapper.employeesToDtos(employees);
	} 
	
	@GetMapping
	public List<EmployeeDto> getEmployeesByNameStartingWith(@RequestParam(required = true) String name) {
		List<Employee> employees = null;
		employees = employeeRepository.findByNameStartingWithIgnoreCase(name);
		return employeeMapper.employeesToDtos(employees);
	} 
	
	@GetMapping
	public List<EmployeeDto> getEmployeesByEntryDate(@RequestParam(required = true) LocalDate from,  @RequestParam(required = true) LocalDate to) {
		List<Employee> employees = null;
		employees = employeeRepository.findByEntryDateBetween(from, to);
		return employeeMapper.employeesToDtos(employees);
	} 
	
	
	@GetMapping("/{id}")
	public EmployeeDto getEmployeeById(@PathVariable long id) {
		Employee employee = employeeService.findById(id)
										   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return employeeMapper.employeeToDto(employee); 
	}
	
	
	@PostMapping
	public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		return employeeMapper.employeeToDto(
						employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
	}
	
	
	@PostMapping("/salaryRaise")
	public double getPayRaise(@RequestBody EmployeeDto employeeDto) {
		return employeeService.getPayRaisePercent(employeeMapper.dtoToEmployee(employeeDto));	
	}
	
	
	@PutMapping("/{id}")
	public EmployeeDto modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		employeeDto.setId(id);
		Employee updatedEmployee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto));
		if (updatedEmployee == null ) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			return employeeMapper.employeeToDto(updatedEmployee);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		employeeService.delete(id);
	}
	
}
