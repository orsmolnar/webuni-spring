package hu.webuni.hr.orsmolnar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.webuni.hr.orsmolnar.service.DefaultEmployeeService;
import hu.webuni.hr.orsmolnar.service.EmployeeService;

@Configuration
@Profile("!smart")
public class DefaultConfiguration {

	@Bean
	public EmployeeService employeeService() {
		return new DefaultEmployeeService();
	}

	
}
