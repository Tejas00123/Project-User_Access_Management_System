package com.Leucine.utils;

public class AppConstant {
	// Database-related constants
	public static final String DB_URL = "jdbc:postgresql://localhost:5432/UAMS";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "BATCH4PM";
    
     // Role constants
    public static final String ROLE_EMPLOYEE = "Employee";
    public static final String ROLE_MANAGER = "Manager";
    public static final String ROLE_ADMIN = "Admin";

    // Access level constants
    public static final String ACCESS_READ = "Read";
    public static final String ACCESS_WRITE = "Write";
    public static final String ACCESS_ADMIN = "Admin";

    // Status constants
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_APPROVED = "Approved";
    public static final String STATUS_REJECTED = "Rejected";

    // General application constants
    public static final String APP_NAME = "Access Management System";
}
