package com.intranet.onlineshop.domain.models.service;

public class BaseUserEditServiceModel {

    private String username;
    private String newPassword;

    public BaseUserEditServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
