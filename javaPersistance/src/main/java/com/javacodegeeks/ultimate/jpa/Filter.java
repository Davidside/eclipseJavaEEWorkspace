package com.javacodegeeks.ultimate.jpa;

import java.util.List;

public class Filter {
	private String name;
	private List<String> params;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
}
