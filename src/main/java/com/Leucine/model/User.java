package com.Leucine.model;
//User.java
import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String role;
   
    //0-param constructor
    public User() {
		// TODO Auto-generated constructor stub
	}
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

