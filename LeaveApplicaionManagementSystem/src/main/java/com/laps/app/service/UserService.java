package com.laps.app.service;

import java.util.List;

import com.laps.app.model.Role;
import com.laps.app.model.User;

public interface UserService {

	  List<User> findAllUsers();
	  User findUser(Integer userId);
	  User createUser(User user);
	  User changeUser(User user);
	  void removeUser(User user);
  void removeUserRoles(Integer userId);
  List<String> findAllUserEmpIDs(); 

	  List<Role> findRolesForUser(Integer userId);
	  List<String> findRoleNamesForUser(Integer userId);
	  List<String> findManagerNameByUID(Integer userId);
	  
	  /**
	   * Return the respective User object if username and password are correct, null otherwise
	   * @param username
	   * @param pwd
	   * @return
	   */
	  User authenticate(String username, String pwd);
	}
