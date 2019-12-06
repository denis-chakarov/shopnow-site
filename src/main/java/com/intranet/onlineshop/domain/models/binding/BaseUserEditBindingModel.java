package com.intranet.onlineshop.domain.models.binding;

public class BaseUserEditBindingModel {

    private String username;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    public BaseUserEditBindingModel() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}
