package com.safir.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.safir.pma.dto.EmployeeProject;
import com.safir.pma.entities.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	@Override
	public List<Employee> findAll();

	
	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_Id)as projectCount "
			+ "FROM employee e left join project_employee pe ON pe.employee_Id=e.employee_Id "
			+ "GROUP BY firstName, lastName ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();
}

