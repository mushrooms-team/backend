package com.springboot.gangnaenglog_backend.dto;

public class PasswordResetRequest {
    private String newPassword;
    private String confirmPassword;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmNewPassword) {
        this.confirmPassword = confirmNewPassword;
    }

}
