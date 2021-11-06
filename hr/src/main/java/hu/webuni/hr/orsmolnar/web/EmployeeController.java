package hu.webuni.hr.orsmolnar.web;

import java.util.List;

import javax.validation.Valid;

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

import hu.webuni.hr.orsmolnar.dto.EmployeeDto;
import hu.webuni.hr.orsmolnar.mapper.EmployeeMapper;
import hu.webuni.hr.orsmolnar.model.Employee;
import hu.webuni.hr.orsmolnar.service.AbstractEmployeeService;
import hu.webuni.hr.orsmolnar.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeMapper employeeMapper;

		
	@GetMapping
	public List<EmployeeDto> getEmployees(@RequestParam(required = false) Integer limit) {
		return limit == null ? employeeMapper.employeesToDtos(employeeService.findAll())
							 : employeeMapper.employeesToDtos(employeeService.findAllByLimit(limit));
	} 
	
	
	@GetMapping("/{id}")
	public EmployeeDto getEmployeeById(@PathVariable long id) {
		Employee employee = employeeService.findById(id);
		if (employee != null) {
			return employeeMapper.employeeToDto(employee); 
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	public EmployeeDto addEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		if (employeeService.findById(employeeMapper.dtoToEmployee(employeeDto).getId()) == null) {
			employeeService.create(employeeMapper.dtoToEmployee(employeeDto));
			Employee newEmployee = employeeService.findById(employeeMapper.dtoToEmployee(employeeDto).getId());
			return employeeMapper.employeeToDto(newEmployee);
		}
		throw new ResponseStatusException(HttpStatus.CONFLICT);
	}
	
	
	@PostMapping("/salaryRaise")
	public double getPayRaise(@RequestBody EmployeeDto employeeDto) {
		return employeeService.getPayRaisePercent(employeeMapper.dtoToEmployee(employeeDto));	
	}
	
	
	@PutMapping("/{id}")
	public EmployeeDto modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		Employee employeeToModify = employeeService.findById(id);
		if (employeeToModify != null) {
			employeeService.update(id, employeeMapper.dtoToEmployee(employeeDto));
			Employee modifiedEmployee = employeeService.findById(employeeMapper.dtoToEmployee(employeeDto).getId());
			return employeeMapper.employeeToDto(modifiedEmployee);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable long id) {
		Employee employeeToDelete = employeeService.findById(id);
		if (employeeToDelete != null) {
			employeeService.delete(id);
		} else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
