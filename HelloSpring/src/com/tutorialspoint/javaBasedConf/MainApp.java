package com.tutorialspoint.javaBasedConf;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.tutorialspoint.beanFactory.HelloWorld;

public class MainApp {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);
		
		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);

		helloWorld.setMessage("Hello World!");
		helloWorld.getMessage();
		
		ctx.registerShutdownHook();

	}

}
