package com.stackroute.moviecruiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.moviecruiser.filter.JwtFilter;

@SpringBootApplication
public class MoviecruiserApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registerBean = new FilterRegistrationBean();
		registerBean.setFilter(new JwtFilter());
		registerBean.addUrlPatterns("/api/*");
		return registerBean;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MoviecruiserApplication.class, args);
	}

}
