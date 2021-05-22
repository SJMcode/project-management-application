package com.safir.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.safir.pma.dao.EmployeeRepository;
import com.safir.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayEmployees(Model models) {
		List<Employee> employees = empRepo.findAll();
		models.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String Employees(Model model) {

		Employee anEmployee = new Employee();
		model.addAttribute("employee", anEmployee);
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String CreateEmployee(Model model, Employee employee) {

		empRepo.save(employee);
		return "redirect:/employees/new";
}
	@GetMapping("/")
	public String displayHome(Model models) {
		
		
		// Querying database for projects
		
		//List<Project> projects = proRepo.findAll();
		//models.addAttribute("projects", projects);
		
		//Querying database for employees
		
		List<Employee> employees = empRepo.findAll();
		models.addAttribute("employees", employees);
		
		return "employees/new-employee";
	}
	
}