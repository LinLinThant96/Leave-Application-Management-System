package com.laps.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laps.app.model.CompensationLeave;
import com.laps.app.repo.CompensationRepository;

@Service
public class CompensationServiceImpl implements CompensationService {
  
  @Resource
  private CompensationRepository compensationRepository;
  

  @Override
  public List<CompensationLeave> findAllcLeave() {
    return compensationRepository.findAll();
  }


  @Override
  @Transactional
  public CompensationLeave findcLeave(Integer ceid) {
    return compensationRepository.findById(ceid).orElse(null);
  }

  @Override
  @Transactional
  public CompensationLeave createcLeave(CompensationLeave cLeave) {
    return compensationRepository.saveAndFlush(cLeave);
  }

  @Override
  @Transactional
  public CompensationLeave changecLeave(CompensationLeave cLeave) {
    return compensationRepository.saveAndFlush(cLeave);
  }

  @Override
  @Transactional
  public void removeCompensationLeave(CompensationLeave cLeave) {
	  compensationRepository.delete(cLeave);
  }

  @Override
  @Transactional
  public List<CompensationLeave> findcLeaveByEID(Integer eid) {
    return compensationRepository.findcLeaveByEID(eid);
  }
  

  @Override
  @Transactional
  public List<CompensationLeave> findPendingcLeaveByEID(Integer eid) {
    return compensationRepository.findPendingcLeaveByEID(eid);
  }
}