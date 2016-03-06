package com.tutorialspoint;

public class HelloWorld {
	private String message1;
	private String message2;

	public void getMessage1() {
		System.out.println("Your Message : " + message1);
	}

	public void setMessage1(String message) {
		this.message1 = message;
	}
	
	public void getMessage2() {
		System.out.println("Your Message : " + message2);
	}

	public void setMessage2(String message) {
		this.message2 = message;
	}
	
	/*
	// @PostConstruct   - can be used without InitHelloWorld class
	public void init(){
		System.out.println("Bean is going through init.");
	}
	
	// @PreDestroy   - can be used without InitHelloWorld class
	public void destroy(){
		System.out.println("Bean will destroy now.");
	}
	*/
	
}
