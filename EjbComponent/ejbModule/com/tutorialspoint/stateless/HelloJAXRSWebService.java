package com.tutorialspoint.stateless;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Session Bean implementation class HelloJAXRSWebService
 */
@Stateless
@LocalBean
public class HelloJAXRSWebService {

    /**
     * Default constructor. 
     */
    public HelloJAXRSWebService() {
        // TODO Auto-generated constructor stub
    }
    
    public String sayHello() {
    	return "Hello";
    }

}
