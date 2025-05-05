package com.springboot.gangnaenglog_backend.dto;

public class PasswordResetRequest {
    private String email;
    private String newPassword;
    private String currentPassword;
    private String confirmPassword;

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
    public String getCurrentPassword() {
        return currentPassword;
    }
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmNewPassword) {
        this.confirmPassword = confirmNewPassword;
    }

}
