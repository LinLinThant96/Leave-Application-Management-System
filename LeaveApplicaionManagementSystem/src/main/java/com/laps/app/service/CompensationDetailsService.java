package com.laps.app.service;

import java.util.List;

import com.laps.app.model.CompensationDetails;


public interface CompensationDetailsService {

	  List<CompensationDetails> findAllCDLeave();

	  CompensationDetails findCDLeave(Integer ceid);

	  CompensationDetails createCDLeave(CompensationDetails CDLeave);

	  CompensationDetails changeCDLeave(CompensationDetails CDLeave);

	  void removeCompensationDetails(CompensationDetails CDLeave);

//	  List<CompensationDetails> findCDLeaveByEID(Integer eid);
//
//	  List<CompensationDetails> findPendingCDLeaveByEID(Integer eid);

	}