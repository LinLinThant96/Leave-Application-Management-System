package com.laps.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity

@Table(name = "employee")
public class Employee implements Serializable {

private static final long serialVersionUID = 6529685098267757670L;
  @Id
  @Column(name = "employeeid")
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer employeeId;
  
  @Column(name="employeename")
  @NotBlank(message = "Name should not be blank")
  private String name;
  
  @Column(name = "managerid")
  private Integer managerId;
  
   @Column
    @NotNull(message = "Compensation leave should not be blank")
  private long compensationleave;
  
  public Integer getEmployeeId() {
	return employeeId;
}

public void setEmployeeId(Integer employeeId) {
	this.employeeId = employeeId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Integer getManagerId() {
	return managerId;
}

public void setManagerId(Integer managerId) {
	this.managerId = managerId;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getEmployeetype() {
	return employeetype;
}

public void setEmployeetype(String employeetype) {
	this.employeetype = employeetype;
}

public Integer getAnnualleaveentitlement() {
	return annualleaveentitlement;
}

public void setAnnualleaveentitlement(Integer annualleaveentitlement) {
	this.annualleaveentitlement = annualleaveentitlement;
}

public Integer getMedicalleave() {
	return medicalleave;
}

public void setMedicalleave(Integer medicalleave) {
	this.medicalleave = medicalleave;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public long getCompensationleave() {
		return compensationleave;
	}

	public void setCompensationleave(long compensationleave) {
		this.compensationleave = compensationleave;
	}

@Column
  @NotBlank(message = "Email should not be blank")
  @Email(message = "Email should be valid")
  private String email;
  @Column
  private String employeetype;
  @Column
  @NotNull(message = "Annual leave should not be blank")
  @Min(value = 0, message = "Annual leave should not be less than 0")
  @Max(value = 18, message = "Annual leave should not be greater than 18")
  private Integer annualleaveentitlement;
  @Column
  @NotNull(message = "Medical leave should not be blank")
  @Min(value = 0, message = "Medical leave should not be less than 0")
  @Max(value = 60, message = "Medical leave should not be greater than 60")
  private Integer medicalleave;
  
  @OneToOne(mappedBy = "employee")
  @JsonIgnore
//  @OneToOne(
//		    mappedBy = "employee",
//		    orphanRemoval = true,
//		    cascade = CascadeType.ALL)
  private User user;
  
//  @OneToMany(mappedBy="employee")
//  private List <LeaveDetails>leavelist;
  
}