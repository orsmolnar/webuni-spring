package hu.webuni.hr.orsmolnar.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import hu.webuni.hr.orsmolnar.dto.EmployeeDto;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {
	
	  private static final String BASE_URI = "/api/employees";
	  
	  @Autowired 
	  WebTestClient webTestClient;
	  
	 
	  @Test 
	  void testThatValidNewEmployeeIsCreated() throws Exception {
	  
	  EmployeeDto newEmployee = new EmployeeDto(10, "Jonas", "developer", 1500, LocalDate.parse("2021-11-03"));
	  
	  List<EmployeeDto> employeesBefore = getAllEmployees();
	  createEmployee(newEmployee).expectStatus()
	  							 .isOk(); 
	  List<EmployeeDto> employeesAfter = getAllEmployees();
	  
	  assertTrue(employeesAfter.containsAll(employeesBefore));
	  employeesAfter.removeAll(employeesBefore);
	  assertEquals(employeesAfter.get(0), newEmployee);
	  }
	  
	  
//	  @Test 
//	  void testThatValidNewEmployeeWithExistingIdCanNotbeCreated() throws Exception {
//	  
//	  EmployeeDto newEmployee = new EmployeeDto(1, "Jonas", "developer", 1500, LocalDate.parse("2021-11-03"));
//	  
//	  assertThrows(ResponseStatusException.class, () -> {createEmployee(newEmployee);});
//	  }
	  
	  
	  @Test 
	  void testThatInvalidEmployeeIsNotCreated() throws Exception
	  {
		  EmployeeDto newEmployee = new EmployeeDto(10, "Jonas", null, 1500, LocalDate.parse("2021-11-03"));
		  
		  List<EmployeeDto> employeesBefore = getAllEmployees();
		  createEmployee(newEmployee).expectStatus()
		  							 .isBadRequest(); 
		  List<EmployeeDto> employeesAfter = getAllEmployees();
		  
		  assertTrue(employeesAfter.containsAll(employeesBefore));
		  assertEquals(employeesAfter.size(), employeesBefore.size());
	  }
	  
	  
	  @Test 
	  void testThatEmployeeCanBeUpdatedWithValidData() throws Exception
	  {
		  EmployeeDto newEmployee = new EmployeeDto(1, "Jonas", "developer", 2000, LocalDate.parse("2021-11-03"));
		  
		  List<EmployeeDto> employeesBefore = getAllEmployees();
		  EmployeeDto employeeBefore = getEmployeeById(newEmployee.getId());
		  modifyEmployee(newEmployee);
		  EmployeeDto employeeAfter = getEmployeeById(newEmployee.getId());
		  List<EmployeeDto> employeesAfter = getAllEmployees();
		  
		  assertEquals(employeesAfter.size(), employeesBefore.size());
		  assertEquals(employeeAfter.getName(), newEmployee.getName());
		  assertEquals(employeeAfter.getTitle(), newEmployee.getTitle());
		  assertEquals(employeeAfter.getSalary(), newEmployee.getSalary());
		  assertEquals(employeeAfter.getEntryDate(), newEmployee.getEntryDate());
		  assertFalse(employeeAfter.equals(employeeBefore));
	  }
	  
	  
	  @Test 
	  void testThatEmployeeCanNotBeUpdatedWithInValidData() throws Exception
	  {
		  EmployeeDto newEmployee = new EmployeeDto(1, "Jonas", null, 2000, LocalDate.parse("2021-11-03"));
		  
		  List<EmployeeDto> employeesBefore = getAllEmployees();
		  EmployeeDto employeeBefore = getEmployeeById(newEmployee.getId());
		  modifyEmployee(newEmployee);
		  EmployeeDto employeeAfter = getEmployeeById(newEmployee.getId());
		  List<EmployeeDto> employeesAfter = getAllEmployees();
		  
		  assertEquals(employeesAfter.size(), employeesBefore.size());
		  assertTrue(employeeAfter.equals(employeeBefore));
	  }
	  
	 
	  private ResponseSpec createEmployee(EmployeeDto employee) { 
		  return webTestClient.post()
		  			   		  .uri(BASE_URI) 
		  			   		  .bodyValue(employee) 
		  			   		  .exchange(); 
	  }
	  
	  
	  private ResponseSpec modifyEmployee(EmployeeDto newEmployee) {
			return webTestClient.put()
								.uri(BASE_URI + "/" + newEmployee.getId())
								.bodyValue(newEmployee)
								.exchange();
		}
	  
	  
	  private List<EmployeeDto> getAllEmployees() { 
	  		List<EmployeeDto> responseList = webTestClient.get()
	  													  .uri(BASE_URI) 
	  													  .exchange() 
	  													  .expectStatus()
	  													  .isOk()
	  													  .expectBodyList(EmployeeDto.class) 
	  													  .returnResult()
	  													  .getResponseBody(); 
	  		return responseList; 
	  }
	  
	  private EmployeeDto getEmployeeById(long id) { 
		  EmployeeDto employee = webTestClient.get()
	  										  .uri(BASE_URI + "/" + id) 
	  										  .exchange() 
	  										  .expectStatus()
	  										  .isOk()
	  										  .expectBody(EmployeeDto.class) 
	  										  .returnResult()
	  										  .getResponseBody(); 
	  	  return employee;
	  }
	 
}
