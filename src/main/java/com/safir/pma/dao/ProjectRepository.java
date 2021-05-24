package com.safir.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.safir.pma.dto.ChartData;
import com.safir.pma.entities.Project;

@RepositoryRestResource(collectionResourceRel=	"apiprojects", path = "apiprojects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{

	
	@Query(nativeQuery=true, value="select stage as label, COUNT(*) as value "+
									"FROM project "+
									"GROUP BY stage")
	public List<ChartData> getProjectStatus(); 
}
