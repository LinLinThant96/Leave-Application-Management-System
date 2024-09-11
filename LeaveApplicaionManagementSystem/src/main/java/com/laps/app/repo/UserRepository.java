package com.laps.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laps.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT DISTINCT e2.name FROM User u, Employee e1, Employee e2 WHERE u.employee.employeeId = e1.employeeId AND e1.managerId = e2.employeeId AND u.userId=:uid")
	//@Query("SELECT u FROM User u WHERE u.userId=:uid")
	List<String> findManagerNamesByUID(@Param("uid") Integer id);

	@Query("SELECT u FROM User u WHERE u.name=:username AND u.password=:pwd")
	User findUserByNamePwd(@Param("username") String username, @Param("pwd") String pwd);
	
	@Query("SELECT DISTINCT u.employee.employeeId FROM User u")
	List<String> findAllUserEmpIDs();
}
