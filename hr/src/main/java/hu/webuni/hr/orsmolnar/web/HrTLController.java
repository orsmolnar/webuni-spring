package hu.webuni.hr.orsmolnar.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.orsmolnar.model.Employee;

@Controller
public class HrTLController {
	
	private List<Employee> allEmployees = new ArrayList<>();
	
//	{
//		allEmployees.add(new Employee(1L, "Bill", "developer", 1000, LocalDate.parse("2011-05-01")));
//		allEmployees.add(new Employee(2L, "Peter", "tester", 1000, LocalDate.parse("2016-05-01")));
//		allEmployees.add(new Employee(3L, "Tom", "scrum master", 1000, LocalDate.parse("2018-09-01")));
//	}
	
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
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable long id) {
		allEmployees.removeIf(emp -> emp.getId().equals(id));
		return "redirect:/employees";
	}
	
	// alkalmazott módosítása 2 lépésben törtrénik: 1.link átvisz egy editáló oldalra (GET), 2.beküldöm a szerkesztett form-ot (POST)
	@GetMapping("/employees/{id}")
	public String editEmployee(@PathVariable long id, Map<String, Object> model) {
		model.put("employee", allEmployees.stream()
										  .filter(emp -> emp.getId().equals(id))
										  .findFirst()
										  .get());
		return "editEmployee";
	}
	
	@PostMapping("/updateEmployee")
	public String updateEmployee(Employee employee) {
		
		for(int i=0; i < allEmployees.size(); i++) {
			if (allEmployees.get(i).getId().equals(employee.getId())) {
				allEmployees.set(i, employee);
				break;
			}
		}
		return "redirect:employees";
	}
}
