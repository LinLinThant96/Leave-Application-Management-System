package com.laps.app.model;


import java.util.List;

import lombok.Data;

@Data
public class UserSession {
  private User user;
  private Employee employee;
  private List<Employee> subordinates;
  
}