package com.Leucine.model;
//Software.java
import java.io.Serializable;

public class Software implements Serializable {
    private int id;
    private String name;
    private String description;
    private String accessLevels;

  //0-param constructor
    public Software() {
		// TODO Auto-generated constructor stub
	}
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAccessLevels() {
        return accessLevels;
    }

    public void setAccessLevels(String accessLevels) {
        this.accessLevels = accessLevels;
    }
}

