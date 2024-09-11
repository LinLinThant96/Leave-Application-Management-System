package com.laps.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laps.app.model.CompensationLeave;


@Repository
public interface CompensationRepository extends JpaRepository<CompensationLeave, Integer> {
	  @Query("SELECT c from CompensationLeave c WHERE c.employeeId = :eid")
	  List<CompensationLeave> findcLeaveByEID(@Param("eid") Integer eid);
	  
	  @Query("SELECT c from CompensationLeave c WHERE c.employeeId = :eid AND (c.status ='SUBMITTED' OR c.status ='UPDATED')")
	  List<CompensationLeave> findPendingcLeaveByEID(@Param("eid") Integer eid);
	  
	  @Query(value = "SELECT * FROM CompensationLeave WHERE status = ?0", nativeQuery = true)
	  List<CompensationLeave> findPendingcLeaveByStatus(Integer status);
	}

