package hu.webuni.hr.orsmolnar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.orsmolnar.model.PositionDetailsByCompany;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompany, Long>{

	public List<PositionDetailsByCompany> findByPositionNameAndCompanyId(String positionName, long id);
}
