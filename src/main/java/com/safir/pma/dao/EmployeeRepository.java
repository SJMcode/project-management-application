package com.safir.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.safir.pma.dto.EmployeeProject;
import com.safir.pma.entities.Employee;

@RepositoryRestResource(collectionResourceRel=	"apiemployees", path = "apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{


	
	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_Id)as projectCount "
			+ "FROM employee e left join project_employee pe ON pe.employee_Id=e.employee_Id "
			+ "GROUP BY firstName, lastName ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();


	public Employee findByEmail(String value);


	public Employee findByEmployeeId(long empId);



}

