# webuni-spring
Three SpringBoot projects implemented during the 12 weeks long webuni training:
Airport app - demo project, implemented together with the trainer
Hr app - homework project, implemented on my own week by week
?? app - exam project

Hr app 1. week:
	- create project (name and artifact: hr; groupId: hu.webuni.spring.orsmolnar; package: hu.webuni.hr.orsmolnar)
	- Employee class (fields, getters, setters, constructor)
	- EmployeeService interface (getPayRaisePercent(Employee))
	- 2 implementations of EmployeeService interface:
			- DefaultEmployeeService.java (everyone gets 5% salary raise)
			- SmartEmployeeService.java (employees get salary raise depending on their entrydate at the company)
	- implementing SalaryService bean which has a method to set the new salary of the employee received in a parameter
	  (EmployeeService bean injected, SalaryService asks EmployeeService for the new salary)
	- implementing method run() in the main class of the app - send requests with some test employees to SalaryService
	- configuration classes with profile="smart" and without profile="smart"
	- modification of getPayRaisePercent() in SmartEmployeeService to get the data (amounts amn limits) from a properties file or a yaml file
	
Hr app 2. week:
	- implementing a GUI with Thymeleaf and Spring MVC - list of emloyees in a table (data stored in memory), add new employee with a form
	- implementing EmployeeDto
	- JSON REST API with /hr/api/employees endpoint
	- testing the REST API with Postman
	
Hr app 3. week:
	- extending the Tymeleaf GUI (link to modify the employee with a form; link to delete the employee)	
	- implementing CompanyDto, that represents a company with a list of its employees
	- JSON REST API with /hr/api/companies endpoint - test it with Postman
	- extnd the REST API to list the companies with and wo a parameter of "full"
	- extend the REST API with new endpoints to create/delete an employee, change all employees in a specific company
	
Hr app 4. week:
	- validating the POST and PUT input data of the EmployeeController with Bean Validation constraints
	- refactoring the controllers to store the Employee and Company data (in memory) in the service classes; and call the corresponding service methods in the controllers
		(MapStruct library used to convert entities to Dto-s and vica versa)
	- integration tests for the EmployeeController PUT and POST methods 
	- generating OPEN API and testing the api on the swagger-ui
	
