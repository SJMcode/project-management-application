package com.safir.pma.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.safir.pma.dao.EmployeeRepository;
import com.safir.pma.entities.Employee;


public class UniqueValidator implements ConstraintValidator<UniqueValue, String>{

	@Autowired
	EmployeeRepository empRepo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		System.out.println("Enter Validation..");
		Employee emp = empRepo.findByEmail(value);
		
		if(emp!=null) 
			return false;
		else 
			return true;
		
		
	}

	
	
}
