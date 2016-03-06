package com.javacodegeeks.ultimate.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PROJECT")
public class Project {
	private Integer id;
	private String title;
	private List<Geek> geeks = new ArrayList<Geek>();
	private Period projectPeriod;
	private ProjectType projectType;
	
	public enum ProjectType {
		FIXED, TIME_AND_MATERIAL
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@ManyToMany(mappedBy = "projects", fetch = FetchType.EAGER)
	public List<Geek> getGeeks() {
		return geeks;
	}
	public void setGeeks(List<Geek> geeks) {
		this.geeks = geeks;
	}
	
	@Embedded
	public Period getProjectPeriod() {
		return projectPeriod;
	}
	public void setProjectPeriod(Period projectPeriod) {
		this.projectPeriod = projectPeriod;
	}
	
	@Enumerated(EnumType.STRING)
	public ProjectType getProjectType() {
		return projectType;
	}
	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}
}
