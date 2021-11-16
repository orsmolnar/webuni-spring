package hu.webuni.hr.orsmolnar.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.orsmolnar.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	//beszédes metódus nevek! - sql query generálódik belőle
	List<Employee> findByTitle(String title);
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	List<Employee> findByEntryDateBetween(LocalDate from, LocalDate to);
	List<Employee> findBySalaryGreaterThan(Integer minSalary);	
}
