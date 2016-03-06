package com.tutorialspoint.injectCollection;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class JavaCollection {
	private List addressList;
	private Set addressSet;
	private Map addressMap;
	private Properties addressProp;
	
	// Prints a returns all the elements of the List
	public List getAddressList() {
		System.out.println("List Elements : " + addressList);
		return addressList;
	}
	
	// a setter method to set List
	public void setAddressList(List addressList) {
		this.addressList = addressList;
	}
	
	// Prints a returns all the elements of the Set
	public Set getAddressSet() {
		System.out.println("Set Elements : " + addressSet);
		return addressSet;
	}
	
	// a setter method to set Set
	public void setAddressSet(Set addressSet) {
		this.addressSet = addressSet;
	}
	
	// Prints a returns all the elements of the Map
	public Map getAddressMap() {
		System.out.println("Map Elements : " + addressMap);
		return addressMap;
	}
	
	// a setter method to set Map
	public void setAddressMap(Map addressMap) {
		this.addressMap = addressMap;
	}
	
	// Prints a returns all the elements of the Property
	public Properties getAddressProp() {
		System.out.println("Property Elements : " + addressProp);
		return addressProp;
	}
	
	// a setter method to set Property
	public void setAddressProp(Properties addressProp) {
		this.addressProp = addressProp;
	}
	
	
	
}
