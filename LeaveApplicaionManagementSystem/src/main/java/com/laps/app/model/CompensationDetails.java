package com.laps.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "compensationdetails")

public class CompensationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer compensationDetailsFormId;

	@Column(name = "employeeId")
	private Integer employeeId;
	@Column
	private LocalDateTime otLeaveStartDate;
	@Column
	private LocalDateTime otLeaveEndDate;
	@Column
	private String timeOfDay;
	@Column
	private long LeaveDuration;
//	@Column
//	private String LeaveStatus;
	
	@Column(name = "decision")
	private String decision;
	
	@Column(name = "status", columnDefinition = "ENUM('SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED')")
	@Enumerated(EnumType.STRING)
	private CompensationEventEnum status;
	
	

	public Integer getCompensationDetailsFormId() {
		return compensationDetailsFormId;
	}

	public void setCompensationDetailsFormId(Integer l) {
		this.compensationDetailsFormId = l;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDateTime getOtLeaveStartDate() {
		return otLeaveStartDate;
	}

	public void setOtLeaveStartDate(LocalDateTime  leaveStartDate) {
		otLeaveStartDate = leaveStartDate;
	}

	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDateTime  getOtLeaveEndDate() {
		return otLeaveEndDate;
	}

	public void setOtLeaveEndDate(LocalDateTime  leaveEndDate) {
		otLeaveEndDate = leaveEndDate;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public long getLeaveDuration() {
		return LeaveDuration;
	}

	public void setLeaveDuration(long l) {
		LeaveDuration = l;
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

	public void setStatus(CompensationEventEnum leaveStatus) {
		status = leaveStatus;
	}

	public CompensationDetails() {
		
		
	}
	
	public CompensationDetails(Integer aId) {
		this.compensationDetailsFormId = aId;
	}

	public CompensationDetails(Integer employeeId, LocalDateTime  leaveStartDate, LocalDateTime  leaveEndDate, String timeofday,
			long leaveDuration, String decision, CompensationEventEnum leaveStatus) {
		super();
		this.employeeId = employeeId;
		this.otLeaveStartDate = leaveStartDate;
		this.otLeaveEndDate = leaveEndDate;
		this.timeOfDay = timeofday;
		this.LeaveDuration = leaveDuration;
		this.decision = decision;
		this.status = leaveStatus;
	}

}
