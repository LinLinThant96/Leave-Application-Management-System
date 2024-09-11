package com.laps.app.repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.laps.app.model.LeaveDetails;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveDetails, String> {
	
	@Query("SELECT ls from LeaveDetails ls where ls.leaveformid= :id")
	 LeaveDetails findByLeaveId(@Param("id") Integer id); 
	
	@Query("SELECT l from LeaveDetails l WHERE l.employeeId = :eid")
	  List<LeaveDetails> findLeavesByEID(@Param("eid") Integer eid);
	
	@Query("SELECT l FROM LeaveDetails l where l.employeeId = :id")
	Page<LeaveDetails> findEmployeeByIdByPage(@Param("id") Integer id, Pageable pageable);

}

/*	@Query("SELECT e FROM Employee e where e.employeeId = :id")
Employee findEmployeeById(@Param("id") Integer id);*/