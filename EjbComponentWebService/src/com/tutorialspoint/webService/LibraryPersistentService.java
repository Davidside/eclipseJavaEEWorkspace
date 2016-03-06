package com.tutorialspoint.webService;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.tutorialspoint.stateless.HelloJAXRSWebService;
import com.tutorialspoint.stateless.LibraryPersistentBean;

@ApplicationPath("/rest")
public class LibraryPersistentService extends Application {

	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(LibraryPersistentBean.class);
        return classes;
	}
}
