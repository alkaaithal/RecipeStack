package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@ComponentScan("com.example.demo")
@EntityScan("com.example.demo")
public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String args[]) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	/*
	 * @Override public void
	 * addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	 * PageableHandlerMethodArgumentResolver resolver = new
	 * PageableHandlerMethodArgumentResolver();
	 * resolver.setOneIndexedParameters(true); argumentResolvers.add(resolver);
	 * super.addArgumentResolvers(argumentResolvers); }
	 */
	
}
