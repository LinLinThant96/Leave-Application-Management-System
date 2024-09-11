package com.laps.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.laps.app.model.CompensationLeave;


public interface CompensationService {

	  List<CompensationLeave> findAllcLeave();

	  CompensationLeave findcLeave(Integer ceid);

	  CompensationLeave createcLeave(CompensationLeave cLeave);

	  CompensationLeave changecLeave(CompensationLeave cLeave);

	  void removeCompensationLeave(CompensationLeave cLeave);

	  List<CompensationLeave> findcLeaveByEID(Integer eid);

	  List<CompensationLeave> findPendingcLeaveByEID(Integer eid);

	}