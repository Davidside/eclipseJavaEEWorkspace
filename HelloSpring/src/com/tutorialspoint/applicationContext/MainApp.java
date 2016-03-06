package com.tutorialspoint.applicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext
				("D:/Dejvi/Programs/eclipseJavaEE/workspace/HelloSpring/src/BeansAppContext.xml");
		
		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.getMessage();
	}

}
