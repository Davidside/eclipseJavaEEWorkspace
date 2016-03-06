package com.ejb3.webservice;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class Main {

	public static void main(String[] args) throws ServiceException, RemoteException  {
		HelloWorldJAXWSServiceLocator locator = new HelloWorldJAXWSServiceLocator();
		HelloWorldJAXWS port = (HelloWorldJAXWS) locator.getPort(HelloWorldJAXWS.class);
		String res = port.sayHello("David");
		System.out.println(res);

	}

}
