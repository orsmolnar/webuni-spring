package hu.webuni.hr.orsmolnar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.orsmolnar.model.Employee;
import hu.webuni.hr.orsmolnar.repository.EmployeeRepository;
import hu.webuni.hr.orsmolnar.repository.PositionDetailsByCompanyRepository;
import hu.webuni.hr.orsmolnar.repository.PositionRepository;

@Service
public class SalaryService {

	private EmployeeService employeeService;
	private PositionRepository positionRepository;
	PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	
	EmployeeRepository employeeRepository;
	
	public SalaryService(EmployeeService employeeService, PositionRepository positionRepository,
			PositionDetailsByCompanyRepository positionDetailsByCompanyRepository,
			EmployeeRepository employeeRepository) {
		super();
		this.employeeService = employeeService;
		this.positionRepository = positionRepository;
		this.positionDetailsByCompanyRepository = positionDetailsByCompanyRepository;
		this.employeeRepository = employeeRepository;
	}

	public void setNewSalary(Employee employee) {
		employee.setSalary((int) Math.round(employee.getSalary()*(employeeService.getPayRaisePercent(employee)*0.01 + 1)));
	}
	
	@Transactional
	public void raiseMinSalary(String positionName, int minSalary, long comapnyId) {
		positionDetailsByCompanyRepository.findByPositionNameAndCompanyId(positionName, comapnyId).forEach(pd -> {
			pd.setMinSalary(minSalary);
			//1.megoldás a company összes employee-ját behúzzuk és végig iterálunk rajtuk, majd egyesével updateljük őket - performancia!!
//			pd.getCompany().getEmployees().forEach(e -> {
//				if(e.getPosition().getName().equals(positioNname) && e.getSalary() < minSalary) {
//					e.setSalary(minSalary);
//				}
//			});
		});
		
		//2.megoldás (JPQL-lel updatelek
		employeeRepository.updateSalaries(positionName, minSalary, comapnyId);
	}
}
