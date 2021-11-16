package hu.webuni.hr.orsmolnar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.orsmolnar.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
