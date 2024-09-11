package com.laps.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.laps.app.model.LeaveDetails;

public interface LeaveService {
	LeaveDetails processLeaveRequest(LeaveDetails Leave);
	LeaveDetails findByLeaveId(Integer id);
    List<LeaveDetails> findAllLeaves();   
    List<LeaveDetails> findLeavesByEID(Integer eid);
    Page<LeaveDetails> findPaginated(Integer eid,int pageNo, int pageSize);
}
