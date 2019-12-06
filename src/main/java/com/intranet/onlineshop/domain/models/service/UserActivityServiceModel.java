package com.intranet.onlineshop.domain.models.service;

import java.time.LocalDateTime;

public class UserActivityServiceModel extends BaseServiceModel {

    private String userIP;
    private String username;
    private String userAuthority;
    private LocalDateTime timeOfActivity;
    private String url;
    private String message;

    public UserActivityServiceModel() {
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(String userAuthority) {
        this.userAuthority = userAuthority;
    }

    public LocalDateTime getTimeOfActivity() {
        return timeOfActivity;
    }

    public void setTimeOfActivity(LocalDateTime timeOfActivity) {
        this.timeOfActivity = timeOfActivity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
