package hu.webuni.hr.orsmolnar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import hu.webuni.hr.orsmolnar.model.Company;
import hu.webuni.hr.orsmolnar.model.Employee;


@Service
public class CompanyService {
	
	private List<Employee> employeesAtFirstCompany = new ArrayList<>();
	{
		employeesAtFirstCompany.add(new Employee(1L, "Bill", "developer", 1200, LocalDate.parse("2011-05-01")));
		employeesAtFirstCompany.add(new Employee(2L, "Peter", "tester", 1100, LocalDate.parse("2016-05-01")));
	}
	
	private Map<Long, Company> companies = new HashMap<>();
	{
		companies.put(1L, new Company(1L, "01-01-123456", "myFirstCompany", "address1", employeesAtFirstCompany));
		companies.put(2L, new Company(2L, "02-02-123456", "mySecondCompany", "address2"));
		companies.put(3L, new Company(3L, "03-03-123456", "myThirdCompany", "address3"));
	}	

	
	public List<Company> findAll() {
		return new ArrayList<>(companies.values());
	}
	
	public Company findById(long id) {
		return companies.get(id);
	}
	
	public Company save(Company company) {
		companies.put(company.getId(), company);
		return company;
	}
	
	public Company addEmployee(Company company, Employee employee) {
		company.getEmployees().add(employee);
		return company;
	}

	public Company modify(Company companyToModify, Company newCompany) {
		newCompany.setId(companyToModify.getId());
		companies.replace(newCompany.getId(), newCompany);
		return newCompany;
	}
	
	public Company changeEmployees(Company company, List<Employee> newEmployees) {
		company.setEmployees(newEmployees);
		return company;
	}
	
	public void delete(long id) {
		companies.remove(id);
	}

	public void deleteEmployees(Company company) {
		company.setEmployees(null);
	}

	public void deleteEmployee(Company companyFromDelete, long eid) throws NoSuchElementException {
		companyFromDelete.getEmployees().remove(companyFromDelete.getEmployees().stream()
				  												 .filter(e -> e.getId()== eid)
				  												 .findFirst()
				  												 .orElseThrow(NoSuchElementException::new));
		
	}
	
}
