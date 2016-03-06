package com.tutorialspoint.requiredAnnot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("BeansRequiredAnnot.xml");
		
		Student student = (Student) context.getBean("student");
		
		System.out.println("Name: " + student.getName());
		System.out.println("Age: " + student.getAge());

	}

}
