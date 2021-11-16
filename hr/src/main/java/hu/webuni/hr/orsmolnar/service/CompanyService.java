package hu.webuni.hr.orsmolnar.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.orsmolnar.model.Company;
import hu.webuni.hr.orsmolnar.model.Employee;
import hu.webuni.hr.orsmolnar.repository.CompanyRepository;
import hu.webuni.hr.orsmolnar.repository.EmployeeRepository;


@Service
public class CompanyService {
	

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	
	public Optional<Company> findById(long id) {
		return companyRepository.findById(id);
	}
	
	@Transactional
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	@Transactional
	public Company update(Company company) {
		if(company.getId() == null || !companyRepository.existsById(company.getId())) {
			return null;
		}
		return companyRepository.save(company);
	}

	@Transactional
	public void delete(long id) {
		companyRepository.deleteById(id);
	}

	
	@Transactional
	public Company addEmployee(long cid, Employee employee) {
		Company company = companyRepository.findById(cid).get();
		company.addEmployee(employee);
		employeeRepository.save(employee);
		return company;
	}
	
	//rárakom a @Transactional-t -> a company és employee is managelt állapotú lesz, nem kell kézzel csatolgatni és mentegetni
	@Transactional
	public Company deleteEmployee(long cid, long eid) {
		Company company = companyRepository.findById(cid).get();
		Employee employee = employeeRepository.findById(eid).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		//a @Transactional miatt nem kell!
		//employeeRepository.save(employee);
		return company;
	}
	
	//transactional lesz később!!
	public Company replaceEmployees(long cid, List<Employee> employees) {
		Company company = companyRepository.findById(cid).get();
		company.getEmployees().forEach(e -> {
			e.setCompany(null);
		});
		company.getEmployees().clear();
		
		employees.forEach(e -> {
			employeeRepository.save(e);
			company.addEmployee(e);
		});
		return company;
	}
	
}
