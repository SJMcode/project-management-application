package com.safir.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.safir.pma.dao.EmployeeRepository;
import com.safir.pma.entities.Employee;
import com.safir.pma.entities.Project;
import com.safir.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeRepository empRepo;

	@GetMapping
	public String displayProjects(Model models) {
		List<Project> projects = proService.getall();
		models.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String projects(Model model) {
		
		List<Employee> employees = empRepo.findAll();;
		model.addAttribute("allEmployees", employees);
		Project aProject = new Project();
		model.addAttribute("project", aProject);
		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(Project project, Model model) {

		// saving to database
		proService.save(project);
		
//		Iterable<Employee>choosenEmployees=empRepo.findAllById(employees);
//		
//		for(Employee emp: choosenEmployees) {
//			
//			emp.setProject(project);
//			empRepo.save(emp);
//		}

		// use redirect to prevent duplicate submissions.
		//return "redirect:/projects/new";
		return "redirect:/projects";

	}
}
