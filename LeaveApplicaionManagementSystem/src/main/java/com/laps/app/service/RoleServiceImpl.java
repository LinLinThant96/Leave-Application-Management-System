package com.laps.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laps.app.model.Role;
import com.laps.app.repo.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleRepository roleRepository;
	@Override
	@Transactional
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	@Transactional
	public Role findRole(String roleId) {
		// TODO Auto-generated method stub
		return roleRepository.findById(roleId).orElse(null);
	}

	@Override
	@Transactional
	public List<String> findAllRolesNames() {
		// TODO Auto-generated method stub
		return roleRepository.findAllRolesNames();
	}

	@Override
	@Transactional
	public List<Role> findRoleByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findRoleByName(name);
	}

}
