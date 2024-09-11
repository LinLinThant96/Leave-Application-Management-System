package com.laps.app.service;

import java.util.List;

import com.laps.app.model.LeaveDetails;

public interface ManagerLeaveService {
	public List<LeaveDetails> GetAllLeaves();

	public List<LeaveDetails> GetAllAppliedLeaves(Integer loginEmpId);

	public LeaveDetails GetLeaveDetailsById(Integer id);

	public void UpdateLeaveDetailsStatus(LeaveDetails ld);
	
	public void UpdateEmployee(LeaveDetails ld);
	
	public List<LeaveDetails> GetLeaveDetailsByEid(Integer eid);
}
