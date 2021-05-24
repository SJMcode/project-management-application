package com.safir.pma.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Bean
	public BCryptPasswordEncoder passwordEncorder() {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		
		return bCryptEncoder;
		
	}
	
	

}
