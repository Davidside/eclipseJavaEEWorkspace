package com.javacodegeeks.ultimate.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_ID_CARD")
public class IdCard {
	private Integer id;
	private String idNumber;
	private Date issueDate;
	private boolean valid;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "ID_NUMBER")
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Column(name = "ISSUE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	@Override
	public String toString() {
		return "IdCard [idNumber=" + idNumber + "]";
	}
	
	@Column(name = "VALID")
	@Convert(converter = BooleanConverter.class)
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
