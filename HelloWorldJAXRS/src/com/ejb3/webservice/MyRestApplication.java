package com.ejb3.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.ejb3.stateless.HelloJAXRSWebService;

@ApplicationPath("/rest")
public class MyRestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(HelloJAXRSWebService.class);
		return classes;
	}
}
