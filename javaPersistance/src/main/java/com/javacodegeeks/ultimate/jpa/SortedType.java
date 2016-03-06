package com.javacodegeeks.ultimate.jpa;

public class SortedType {
	private static final String ASC = "ascending";
	private static final String DESC = "descending";
	private String sorting;
	
	public String getSorting() {
		return sorting;
	}
	public void setSortingASC() {
		this.sorting = ASC;
	}
	public void setSortingDESC() {
		this.sorting = DESC;
	}
}
