package com.safir.pma.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.safir.pma.entities.Employee;
import com.safir.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayEmployees(Model models) {
		Iterable<Employee> employees = empService.getall();
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
	public String CreateEmployee(Model model, @Valid Employee employee, Errors error) {

			if(error.hasErrors())
				return "employees/new-employee";
		empService.save(employee);
		return "redirect:/employees";
}
	@GetMapping("/")
	public String displayHome(Model models) {
		
		
		// Querying database for projects
		
		//List<Project> projects = proRepo.findAll();
		//models.addAttribute("projects", projects);
		
		//Querying database for employees
		
		Iterable<Employee> employees = empService.getall();
		models.addAttribute("employees", employees);
		
		return "employees/new-employee";
	}
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long empId, Model model) {
			
			Employee theEmp=empService.findByEmployeeId(empId);
			model.addAttribute("employee",theEmp);
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") long empId) {
			Employee theEmp=empService.findByEmployeeId(empId);
			empService.delete(theEmp);
		return "redirect:/employees";
	}
}