package com.laps.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laps.app.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{
	  @Query("SELECT r.name FROM Role r")
	  List<String> findAllRolesNames();
	  
	  @Query("SELECT r FROM Role r WHERE r.name = :name")
	  List<Role> findRoleByName(@Param("name") String name);
	}
