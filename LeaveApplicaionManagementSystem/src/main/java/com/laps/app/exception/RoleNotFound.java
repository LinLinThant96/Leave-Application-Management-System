package com.laps.app.exception;

public class RoleNotFound extends Exception {
	  private static final long serialVersionUID = 1L;
	  
	  public RoleNotFound() {}
	  
	  public RoleNotFound(String msg) {
	    super(msg);
	  }
	}
