// EmailVerificationRequest.java
package com.springboot.gangnaenglog_backend.dto;

public class EmailVerificationRequest {
    private String email;
    private int code;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}
