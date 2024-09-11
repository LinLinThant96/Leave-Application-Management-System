package com.laps.app.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;



@Entity
@Data
@Table(name = "role")
public class Role implements Serializable {
  private static final long serialVersionUID = 6529685098267757690L;
  
  @Id
  @Column(name = "roleid")
  private String roleId;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "description")
  private String description;
  
 // @ManyToMany(mappedBy = "roleSet")
  //private List<User> userRoles;
  
  /*public Role() {}
  
  public Role(Integer roleId) {
    this.roleId = roleId;
  }
  
  public Role(Integer roleId, String name, String description) {
    this.roleId = roleId;
    this.name = name;
    this.description = description;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public int hashCode() {
    return Objects.hash(roleId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Role other = (Role) obj;
    return Objects.equals(roleId, other.roleId);
  }*/
}