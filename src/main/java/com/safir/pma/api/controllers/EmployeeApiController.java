package com.safir.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safir.pma.dao.EmployeeRepository;
import com.safir.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public Iterable<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return empRepo.findById(id).get();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody @Valid Employee employee) {
		return empRepo.save(employee);
	}
	
	@PutMapping(path="/{id}", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody @Valid Employee employee) {
		
		return empRepo.save(employee);
		
	}
	
	@PatchMapping(path="/{id}", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee partialUpdate(@PathVariable("id") Long id,@RequestBody @Valid Employee patchEmployee) {
		
		
		Employee emp= empRepo.findById(id).get();
		if(patchEmployee.getFirstName()!=null) {
			emp.setFirstName(patchEmployee.getFirstName());
		}
		if(patchEmployee.getLastName()!=null) {
			emp.setLastName(patchEmployee.getLastName());
		}
		if(patchEmployee.getEmail()!=null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		
		return empRepo.save(emp);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		
		try {
		empRepo.deleteById(id);
		}
		catch(EmptyResultDataAccessException e){
			
		}
	}
	
//	@GetMapping(params= {"page","size"})
//	@ResponseStatus(HttpStatus.OK)
//	public long findPaginatedEmployees(@RequestParam("page") int page,
//			@RequestParam("size") int size){
//		
//		Pageable pageAndSize = (Pageable) PageRequest.of(page, size);
//		
//		//return empRepo.findAll(pageAndSize);
//		return empRepo.count();
//
//		
//		
//	}
}

