package com.safir.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safir.pma.dao.EmployeeRepository;
import com.safir.pma.dao.ProjectRepository;
import com.safir.pma.dto.ChartData;
import com.safir.pma.dto.EmployeeProject;
import com.safir.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model models) throws JsonProcessingException {
		
		models.addAttribute("versionNumber", ver);
		// Querying database for projects
		//Map<String, Object> map = new HashMap<>();
		Iterable<Project> projects = proRepo.findAll();
		models.addAttribute("projects", projects);
		
		List<ChartData> projectdata = proRepo.getProjectStatus();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString=objectMapper.writeValueAsString(projectdata);
		//[["NOTSTARTED",1], ["INPROGRESS",1], ["COMPLETED",1]]
		models.addAttribute("projectStatusCnt", jsonString);
		
		//Querying database for employees
		
		List<EmployeeProject> employeeProjectCnt = empRepo.employeeProjects();
		models.addAttribute("employeeListProjectCnt", employeeProjectCnt);
		
		return "main/home";
	}
}
