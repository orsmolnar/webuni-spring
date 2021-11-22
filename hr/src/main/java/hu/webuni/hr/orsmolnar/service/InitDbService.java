package hu.webuni.hr.orsmolnar.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.orsmolnar.model.Company;
import hu.webuni.hr.orsmolnar.model.Employee;
import hu.webuni.hr.orsmolnar.model.Position;
import hu.webuni.hr.orsmolnar.model.PositionDetailsByCompany;
import hu.webuni.hr.orsmolnar.model.Qualification;
import hu.webuni.hr.orsmolnar.repository.CompanyRepository;
import hu.webuni.hr.orsmolnar.repository.EmployeeRepository;
import hu.webuni.hr.orsmolnar.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.orsmolnar.repository.PositionRepository;

@Service
public class InitDbService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	
	//clearDb() - ez a funkció a properties-ben lett beállítva: spring.jpa.hibernate.ddl-auto=create az auto helyett
	
	@Transactional
	public void initDb() {
		
		Position developer = positionRepository.save(new Position("developer", Qualification.UNIVERSITY));
		Position tester = positionRepository.save(new Position("tester", Qualification.HIGH_SCHOOL));
		
		Employee newEmployee1 = employeeRepository.save(new Employee(null, "Max1", 2000, LocalDate.now()));
		newEmployee1.setPosition(developer);
		Employee newEmployee2 = employeeRepository.save(new Employee(null, "Max2", 1800, LocalDate.now()));
		newEmployee2.setPosition(tester);
		
		Company newCompany = companyRepository.save(new Company(null, "COMPANY_111", "myFirstCompany", "address_1", null));
		newCompany.addEmployee(newEmployee1);
		newCompany.addEmployee(newEmployee2);
		
		PositionDetailsByCompany pd1 = new PositionDetailsByCompany();
		pd1.setCompany(newCompany);
		pd1.setMinSalary(2000);
		pd1.setPosition(developer);
		positionDetailsByCompanyRepository.save(pd1);
		
		PositionDetailsByCompany pd2 = new PositionDetailsByCompany();
		pd2.setCompany(newCompany);
		pd2.setMinSalary(1800);
		pd2.setPosition(tester);
		positionDetailsByCompanyRepository.save(pd2);
	}

}
