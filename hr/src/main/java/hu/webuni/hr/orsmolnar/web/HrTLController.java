package hu.webuni.hr.orsmolnar.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.orsmolnar.model.Employee;

@Controller
public class HrTLController {
	
	private List<Employee> allEmployees = new ArrayList<>();
	
	{
		allEmployees.add(new Employee(1L, "Bill", "developer", 1000, LocalDate.parse("2011-05-01")));
		allEmployees.add(new Employee(2L, "Peter", "tester", 1000, LocalDate.parse("2016-05-01")));
		allEmployees.add(new Employee(3L, "Tom", "scrum master", 1000, LocalDate.parse("2018-09-01")));
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/employees")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees", allEmployees);
		model.put("newEmployee", new Employee());
		model.put("newEmployees", allEmployees);
		return "employees";
	}
	
	@PostMapping("/employees")
	public String addEmployee(Employee employee) {
		allEmployees.add(employee);
		return "redirect:employees";
	}
	
}
