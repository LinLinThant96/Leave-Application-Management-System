package com.laps.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laps.app.model.CompensationDetails;


@Repository
public interface CompensationDetailsRepository extends JpaRepository<CompensationDetails, Integer> {
	  //@Query("SELECT c from compensationdetails c WHERE c.employeeId = :eid")
	//@Query("SELECT c from compensationdetails c ")
	 // List<CompensationDetails> findCDLeaveByEID(@Param("eid") Integer eid);
	  
	//  @Query("SELECT c from compensationdetails c WHERE c.employeeId = :eid AND (c.status ='SUBMITTED' OR c.status ='UPDATED')")
	//  List<CompensationDetails> findPendingCDLeaveByEID(@Param("eid") Integer eid);
	  
	 // @Query(value = "SELECT * FROM compensationdetails WHERE status = ?0", nativeQuery = true)
	 // List<CompensationDetails> findPendingCDLeaveByStatus(Integer status);
	}

