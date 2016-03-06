package com.tutorialspoint.javaBasedConf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {

	@Bean(initMethod = "init", destroyMethod = "destroy")
	// @Scope("prototype")   - can be used to change scope
	public HelloWorld helloWorld() {
		return new HelloWorld();
	}
}
