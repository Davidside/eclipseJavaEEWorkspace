package com.tutorialspoint.declarativeTrans;

import java.util.List;

import javax.sql.DataSource;

public interface StudentDao {
	/**
	 * This is method to be used to initialize
	 * database resources ie. connection
	 */
	public void setDataSource(DataSource ds);
	
	/**
	 * This is the method to be used to create
	 * a record in the Student and Marks tables.
	 */
	public void create(String name, Integer Age, Integer marks, Integer year);
	
	/** 
	* This is the method to be used to list down
	* all the records from the Student and Marks tables.
	*/
	public List<StudentMarks> listStudents();
	
}
