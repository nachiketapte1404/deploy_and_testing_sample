package com.example.deploytesting.dto;

public class LoginRequest {
    private String username;
    private String password;
    // Getters and Setters (or use @Data if using Lombok)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}