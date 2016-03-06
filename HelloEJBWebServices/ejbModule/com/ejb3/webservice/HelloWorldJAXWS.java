package com.ejb3.webservice;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Session Bean implementation class HelloWorldJAXWS
 */
@WebService
@Stateless
@LocalBean
public class HelloWorldJAXWS {

    /**
     * Default constructor. 
     */
    public HelloWorldJAXWS() {
        // TODO Auto-generated constructor stub
    }
    
    @WebMethod
    public String sayHello(@WebParam(name = "name") String name) {
    	return "Hello " + name + "!";
    }

}
