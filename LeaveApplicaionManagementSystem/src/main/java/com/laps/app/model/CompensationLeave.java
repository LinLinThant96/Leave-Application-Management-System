package com.laps.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Compensationleave")
public class CompensationLeave {

	/** Attributes for Course **/
	@Id
	@Column(name = "CompensationLeaveId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CompensationLeaveId;

	@Basic
	@Column(name = "employeeid")
	private Integer employeeId;

	@Column(name = "compensationHours")
	private Long compensationHours;

	@Column(name = "cLeaveStartDate")
	// @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime cLeaveStartDate;

	@Column(name = "cLeaveEndDate")
	// @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")

	private LocalDateTime cLeaveEndDate;

	@Basic
	@Column(name = "justification")
	private String justification;

	@Column(name = "decision")
	private String decision;

	@Column(name = "status", columnDefinition = "ENUM('SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED')")
	@Enumerated(EnumType.STRING)
	private CompensationEventEnum status;

	public CompensationLeave() {
	}

	public CompensationLeave(int CompensationLeaveId) {
		this.CompensationLeaveId = CompensationLeaveId;
	}

	public CompensationLeave(Integer employeeId, Long compensationHours, LocalDateTime cLeaveStartDate,
			LocalDateTime cLeaveEndDate, String justification, String decision, CompensationEventEnum status) {
		super();
		this.employeeId = employeeId;
		this.compensationHours = compensationHours;

		this.cLeaveStartDate = cLeaveStartDate;
		this.cLeaveEndDate = cLeaveEndDate;
		this.justification = justification;
		this.decision = decision;
		this.status = status;
	}

	public int getCompensationLeaveId() {
		return CompensationLeaveId;
	}

	public void setCompensationLeaveId(int CompensationLeaveId) {
		this.CompensationLeaveId = CompensationLeaveId;
	}
	//
//  public String getString1() {
//	    return string1;
//	  }
//  public void setString1(LocalDateTime cLeaveStartDate) {
//	    this.string1 = cLeaveStartDate;
//	  }
//  
//  public String getString2() {
//	    return string2;
//	  }
//public void setString2(LocalDateTime cLeaveEndDate) {
//	    this.string2 = (String)cLeaveEndDate;
//	  }
//	  

	//

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer integer) {
		this.employeeId = integer;
	}

	public long getCompensationHours() {
		return compensationHours;

	}

	public void setCompensationHours(long l) {
		this.compensationHours = l;
	}

	public LocalDateTime getcLeaveStartDate() {
		return cLeaveStartDate;
	}

	public void setcLeaveStartDate(LocalDateTime cLeaveStartDate) {
		this.cLeaveStartDate = cLeaveStartDate;
	}

	public LocalDateTime getcLeaveEndDate() {
		return cLeaveEndDate;
	}

	public void setcLeaveEndDate(LocalDateTime cLeaveEndDate) {
		this.cLeaveEndDate = cLeaveEndDate;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public CompensationEventEnum getStatus() {
		return status;
	}

	public void setStatus(CompensationEventEnum status) {
		this.status = status;
	}

}