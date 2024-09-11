package com.laps.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laps.app.model.CompensationDetails;
import com.laps.app.repo.CompensationDetailsRepository;

@Service
public class CompensationDetailsServiceImpl implements CompensationDetailsService {

  @Resource
  private CompensationDetailsRepository compensationDetailsRepository;
  

  @Override
  public List<CompensationDetails> findAllCDLeave() {
    return compensationDetailsRepository.findAll();
  }


  @Override
  @Transactional
  public CompensationDetails findCDLeave(Integer ceid) {
    return compensationDetailsRepository.findById(ceid).orElse(null);
  }

  @Override
  @Transactional
  public CompensationDetails createCDLeave(CompensationDetails cLeave) {
    return compensationDetailsRepository.saveAndFlush(cLeave);
  }

  @Override
  @Transactional
  public CompensationDetails changeCDLeave(CompensationDetails cLeave) {
    return compensationDetailsRepository.saveAndFlush(cLeave);
  }

  @Override
  @Transactional
  public void removeCompensationDetails(CompensationDetails cLeave) {
	  compensationDetailsRepository.delete(cLeave);
  }

//  @Override
//  @Transactional
//  public List<CompensationDetails> findCDLeaveByEID(Integer eid) {
//    return compensationDetailsRepository.findCDLeaveByEID(eid);
//  }
//  
//
//  @Override
//  @Transactional
//  public List<CompensationDetails> findPendingCDLeaveByEID(Integer eid) {
//    return compensationDetailsRepository.findPendingCDLeaveByEID(eid);
//  }
}