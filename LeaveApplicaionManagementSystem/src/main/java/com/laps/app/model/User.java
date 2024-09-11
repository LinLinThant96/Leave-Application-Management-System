package com.laps.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 6529685098267757680L;
	@Id
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@NotBlank(message = "{error.user.name.empty}")
	@Column(name = "name")
	private String name;

	@NotBlank(message = "{error.user.password.empty}")
	@Column(name = "password")
	private String password;

//  @Column(name = "employeeid")
//  private String employeeId;


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	//@OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	private Employee employee;

	@ManyToMany(targetEntity = Role.class, cascade = { CascadeType.ALL, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "userrole", joinColumns = {
			@JoinColumn(name = "userid", referencedColumnName = "userid") }, inverseJoinColumns = {
					@JoinColumn(name = "roleid", referencedColumnName = "roleid") })
	private List<Role> roleSet;

	public List<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(List<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public List<String> getRoleNames() {
		List<String> roleList = new ArrayList<>();
		roleSet.forEach(role -> roleList.add(role.getName()));

		return roleList;
	}

	public Integer getEmployeeId() {
		return employee.getEmployeeId();
	}

	public void setEmployeeId(Integer employeeId) {
		employee.setEmployeeId(employeeId);
	}
public User() {
		
	}

	public User(Employee emp) {
		// TODO Auto-generated constructor stub
		this.employee = emp;
	}
	

}