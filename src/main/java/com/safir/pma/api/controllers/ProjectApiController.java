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

import com.safir.pma.dao.ProjectRepository;
import com.safir.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@GetMapping
	public Iterable<Project> getAllprojects(){
		return proRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getprojectById(@PathVariable("id") Long id) {
		return proRepo.findById(id).get();
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody @Valid Project project) {
		return proRepo.save(project);
	}
	
	@PutMapping(path="/{id}", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody @Valid Project project) {
		
		return proRepo.save(project);
		
	}
	
	@PatchMapping(path="/{id}", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project partialUpdate(@PathVariable("id") Long id,@RequestBody @Valid Project patchProject) {
		
		
		Project pro= proRepo.findById(id).get();
		
		if(patchProject.getDescription()!=null) {
			pro.setDescription(patchProject.getDescription());
		}
		if(patchProject.getName()!=null) {
			pro.setName(patchProject.getName());
		}
		if(patchProject.getStage()!=null) {
			pro.setStage(patchProject.getStage());
		}
		
		return proRepo.save(pro);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		
		try {
			proRepo.deleteById(id);
		}
		catch(EmptyResultDataAccessException e){
			
		}
	}
	
	
}

