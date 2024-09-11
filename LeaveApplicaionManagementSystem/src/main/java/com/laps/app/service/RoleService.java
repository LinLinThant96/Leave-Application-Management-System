package com.laps.app.service;

import java.util.List;
import com.laps.app.model.Role;

public interface RoleService {
	List<Role> findAllRoles();
	Role findRole(String roleId);
	List<String> findAllRolesNames();
	List<Role> findRoleByName(String name);
}

