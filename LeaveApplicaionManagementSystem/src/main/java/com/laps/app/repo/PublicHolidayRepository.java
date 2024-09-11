package com.laps.app.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laps.app.model.PublicHoliday;
import com.laps.app.model.Role;

@Repository
public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, Integer>{
	  
	}