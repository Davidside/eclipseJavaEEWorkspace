package com.tutorialspoint.injectBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("BeansInject.xml");
		
		TextEditor te = (TextEditor) context.getBean("textEditor");
		
		te.spellCheck();

	}

}
