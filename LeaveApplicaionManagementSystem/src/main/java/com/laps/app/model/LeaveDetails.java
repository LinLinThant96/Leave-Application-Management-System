package com.laps.app.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="leavedetails")
public class LeaveDetails implements Serializable {

	private static final long serialVersionUID = 6529685098267757670L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer leaveformid;
	
	@Column(name = "employeeid")
	private Integer employeeId;
	
	@Column
	//@NotBlank(message = "{error.leavedetails.leavetype.empty}")
	private String leavetype;
	
	@NotBlank(message = "{error.leavedetails.leavecategory.empty}")
	@Column
	private String leavecategory;
	
	@Column
	@NotNull(message = "Leave Start Date must be provided")
	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leavestartdate;
	
	@Column
	@NotNull(message = "Leave End Date must be provided")
	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate leaveenddate;
	
	@Column
	private  int leaveduration;
	
	@NotBlank(message = "{error.leavedetails.leavereason.empty}")
	@Column
	private String leavereason;
	
	
	@Column
	private String managercomment;
	
	@Column
	private String workdissemination;
	
	@Digits(integer=10,fraction=0)
	@Column
	private Integer contactnumber;
	
	private String Decision;
	private String employeeName;

	
	/*
	 * @ManyToOne private Employee employee;
	 */
	
	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@Column(name = "leavestatus", columnDefinition = "ENUM('APPLIED','DELETED', 'UPDATED', 'CANCELLED','APPROVED','REJECTED')")
	@Enumerated(EnumType.STRING)
	private LeaveEventEnum leavestatus;
	
	public LeaveDetails() {}
	
	public LeaveDetails(Integer leaveFormId, Integer employeeId, String leaveType, @FutureOrPresent LocalDate leaveStartDate, @FutureOrPresent LocalDate leaveEndDate,
			int leaveDuration, LeaveEventEnum leaveStatus, String leaveReason, String managerComment, String workDissemination,
			String leaveCategory, Integer contactNumber) {
		super();
		this.employeeId = employeeId;
		this.leavetype = leaveType;
		this.leavestartdate = leaveStartDate;
		this.leaveenddate = leaveEndDate;
		this.leaveduration = leaveDuration;
		this.leavestatus = leaveStatus;
		this.leavereason = leaveReason;
		this.managercomment = managerComment;
		this.workdissemination = workDissemination;
		this.leavecategory = leaveCategory;
		this.contactnumber = contactNumber;
	}
	
	public String getDecision() {
		return Decision;
	}

	public void setDecision(String decision) {
		Decision = decision;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public LeaveEventEnum getLeavestatus() {
		return leavestatus;
	}

	public void setLeavestatus(LeaveEventEnum leavestatus) {
		this.leavestatus = leavestatus;
	}

	public Integer getLeaveformid() {
		return leaveformid;
	}

	public void setLeaveformid(Integer leaveformid) {
		this.leaveformid = leaveformid;
	}

	public String getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public String getLeavecategory() {
		return leavecategory;
	}

	public void setLeavecategory(String leavecategory) {
		this.leavecategory = leavecategory;
	}

	public LocalDate getLeavestartdate() {
		return leavestartdate;
	}

	public void setLeavestartdate(LocalDate leavestartdate) {
		this.leavestartdate = leavestartdate;
	}

	public LocalDate getLeaveenddate() {
		return leaveenddate;
	}

	public void setLeaveenddate(LocalDate leaveenddate) {
		this.leaveenddate = leaveenddate;
	}

	public int getLeaveduration() {
		return leaveduration;
	}

	public void setLeaveduration(int leaveduration) {
		this.leaveduration = leaveduration;
	}

	public String getLeavereason() {
		return leavereason;
	}

	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}

	public void setManagercomment(String managercomment) {
		this.managercomment = managercomment;
	}
	
	public String getManagercomment() {
		return managercomment;
	}

	public String getWorkdissemination() {
		return workdissemination;
	}

	public void setWorkdissemination(String workdissemination) {
		this.workdissemination = workdissemination;
	}

	public Integer getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(Integer contactnumber) {
		this.contactnumber = contactnumber;
	}

	/*
	 * public Employee getEmployee() { return employee; }
	 * 
	 * public void setEmployee(Employee employee) { this.employee = employee; }
	 */
}
