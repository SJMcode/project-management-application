package com.safir.pma.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueValidator.class)
public @interface UniqueValue {
	
	String message() default "Unique Constraint violated";
	
	Class<?>[] group() default{};
	
	Class<? extends Payload> [] payload() default{};

}
