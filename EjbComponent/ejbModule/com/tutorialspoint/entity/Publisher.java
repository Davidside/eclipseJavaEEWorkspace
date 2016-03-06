package com.tutorialspoint.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Publisher implements Serializable {

	private String name;
	private String address;
	
	public Publisher() {}

	public Publisher(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	} 

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return name + "," + address;
	}	
}
