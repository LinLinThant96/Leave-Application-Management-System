package com.laps.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laps.app.model.LeaveDetails;
import com.laps.app.repo.LeaveRepository;


@Service
public class LeaveServiceImpl implements LeaveService {
	
	@Resource
	private LeaveRepository leaveRepository;
	
	@Autowired
	private EmployeeService empService;
	
	  @Override
	  @Transactional
	 public LeaveDetails processLeaveRequest(LeaveDetails leave) {
		 return leaveRepository.saveAndFlush(leave);
	 }
	 @Override
	 public LeaveDetails findByLeaveId(Integer  id) {
		 return leaveRepository.findByLeaveId(id);
		 }

	 @Override
	  public List<LeaveDetails> findAllLeaves() {
	    return leaveRepository.findAll();
	  }
	  
	  @Override
	  @Transactional
	  public List<LeaveDetails> findLeavesByEID(Integer eid) {
	    return leaveRepository.findLeavesByEID(eid);
	  }
 
	  @Override
	  public Page<LeaveDetails> findPaginated(Integer eid,int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("leaveformid").descending());
		return this.leaveRepository.findEmployeeByIdByPage(eid, pageable);
		//return this.leaveRepository.findAll(pageable);
	  }
	 
	 
}
