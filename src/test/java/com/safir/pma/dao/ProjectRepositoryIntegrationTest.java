package com.safir.pma.dao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.safir.pma.entities.Project;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ProjectRepositoryIntegrationTest {
	
@Autowired
ProjectRepository proRepo;

@Test
public void ifNewProjectSaved_thenSuccess() {
	
	Project newProject = new Project("New Test Project", "COMPLETED", "Test Project");
	
	proRepo.save(newProject);
	
	assertEquals(1, proRepo.findAll().size());
}
	

}
