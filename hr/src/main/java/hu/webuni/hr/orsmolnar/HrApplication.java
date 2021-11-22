package hu.webuni.hr.orsmolnar;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.orsmolnar.model.Employee;
import hu.webuni.hr.orsmolnar.service.InitDbService;
import hu.webuni.hr.orsmolnar.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{

	@Autowired
	SalaryService salaryService;
	
	@Autowired
	InitDbService initDbService;

	Employee employee1 = new Employee(1L, "Joe", 1000, LocalDate.parse("2010-01-02"));
	Employee employee2 = new Employee(2L, "Jane", 1000, LocalDate.parse("2015-01-02"));
	Employee employee3 = new Employee(3L, "Mary", 1000, LocalDate.parse("2018-06-15"));
	Employee employee4 = new Employee(4L, "Bob", 1000, LocalDate.parse("2020-01-02"));
	Employee employee5 = new Employee(5L, "Kate", 1000, LocalDate.parse("2021-05-01"));

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		salaryService.setNewSalary(employee1);
		salaryService.setNewSalary(employee2);
		salaryService.setNewSalary(employee3);
		salaryService.setNewSalary(employee4);
		salaryService.setNewSalary(employee5);
		
		System.out.println(employee1.getName() + " " + employee1.getSalary());
		System.out.println(employee2.getName() + " " + employee2.getSalary());
		System.out.println(employee3.getName() + " " + employee3.getSalary());
		System.out.println(employee4.getName() + " " + employee4.getSalary());
		System.out.println(employee5.getName() + " " + employee5.getSalary());
	
		
		initDbService.initDb();
	}
}
