package com.medconnect.emergency.api;

public class LoginResponse {

    private String status;
    private String role;
    private int userId;
    private String message;

    public String getStatus() { return status; }
    public String getRole() { return role; }
    public int getUserId() { return userId; }
    public String getMessage() { return message; }
}
