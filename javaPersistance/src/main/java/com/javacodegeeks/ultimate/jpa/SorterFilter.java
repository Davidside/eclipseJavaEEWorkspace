package com.javacodegeeks.ultimate.jpa;

import java.util.List;

public class SorterFilter {
	private String name;
	private List<String> params;
	private SortedType type;
	
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
	public SortedType getType() {
		return type;
	}
	public void setType(SortedType type) {
		this.type = type;
	}
}
