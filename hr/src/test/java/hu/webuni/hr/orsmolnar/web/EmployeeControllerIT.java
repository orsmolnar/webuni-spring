package hu.webuni.hr.orsmolnar.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
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
	  
	  EmployeeDto newEmployee = new EmployeeDto(0L, "Jonas", "developer", 1500, LocalDate.parse("2021-11-03"));
	  
	  List<EmployeeDto> employeesBefore = getAllEmployees();
	  createEmployee(newEmployee).expectStatus()
	  							 .isOk(); 
	  List<EmployeeDto> employeesAfter = getAllEmployees();
	  
//    ehhez meg kellene írni az EmployeeDto equals()-ét	  
//	  assertTrue(employeesAfter.containsAll(employeesBefore));
//	  employeesAfter.removeAll(employeesBefore);
//	  assertEquals(employeesAfter.get(0), newEmployee);
	  
	  assertThat(employeesAfter.size() == (employeesBefore.size()+1));
	  assertThat(employeesAfter.get(employeesAfter.size()-1))
	  						   .usingRecursiveComparison()
	  						   .ignoringFields("id")
	  						   .isEqualTo(newEmployee);
	  
	  }
	  
	  
	  @Test 
	  void testThatInvalidEmployeeIsNotCreated() throws Exception {
		  EmployeeDto newInvalidEmployee = newInvalidEmployee();
		  
		  List<EmployeeDto> employeesBefore = getAllEmployees();
		  createEmployee(newInvalidEmployee).expectStatus()
		  							 		.isBadRequest(); 
		  List<EmployeeDto> employeesAfter = getAllEmployees();
		  
		  assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
	  }
	  
	  private EmployeeDto newInvalidEmployee() {
		  return new EmployeeDto(0L, "Jonas", "", 1500, LocalDate.parse("2021-11-03"));
	  }
	  
	  
	  @Test 
	  void testThatEmployeeCanBeUpdatedWithValidData() throws Exception {
		  EmployeeDto newEmployee = new EmployeeDto(0L, "Jonas", "developer", 2000, LocalDate.parse("2021-11-03"));
		  
		  EmployeeDto savedEmployee = createEmployee(newEmployee).expectStatus()
				  												 .isOk()
				  												 .expectBody(EmployeeDto.class)
				  												 .returnResult()
				  												 .getResponseBody();
		  List<EmployeeDto> employeesBefore = getAllEmployees();
		  savedEmployee.setName("something else");
		  modifyEmployee(savedEmployee).expectStatus()
		  							   .isOk();
		  List<EmployeeDto> employeesAfter = getAllEmployees();
		  
		  assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
		  assertThat(employeesAfter.get(employeesAfter.size()-1))
		  						   .usingRecursiveComparison()
		  						   .isEqualTo(savedEmployee);
	  }
	  
	  
	  @Test 
	  void testThatEmployeeCanNotBeUpdatedWithInValidData() throws Exception {
		  EmployeeDto newEmployee = new EmployeeDto(0L, "Jonas", "developer", 2000, LocalDate.parse("2021-11-03"));
		  
		  EmployeeDto savedEmployee = createEmployee(newEmployee).expectStatus()
				  												 .isOk()
				  												 .expectBody(EmployeeDto.class)
				  												 .returnResult()
				  												 .getResponseBody();
		  List<EmployeeDto> employeesBefore = getAllEmployees();
		  
		  EmployeeDto invalidEmployee = newInvalidEmployee();
		  invalidEmployee.setId(savedEmployee.getId());
		  modifyEmployee(invalidEmployee).expectStatus()
		  								 .isBadRequest();

		  List<EmployeeDto> employeesAfter = getAllEmployees();

		  assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
		  assertThat(employeesAfter.get(employeesAfter.size()-1))
								   .usingRecursiveComparison()
								   .isEqualTo(savedEmployee);
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
	  		Collections.sort(responseList, Comparator.comparing(EmployeeDto::getId));
	  		return responseList; 
	  }
}
