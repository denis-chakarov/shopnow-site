package com.intranet.onlineshop.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Class used for representing the table user_activity_logger in the database
 */
@Entity
@Table(name = "user_activity_logger")
public class UserActivity extends BaseEntity {

    private String userIP;
    private String username;
    private String userAuthority;
    private LocalDateTime timeOfActivity;
    private String url;
    private String message;

    public UserActivity() {
    }

    @Column(name = "user_ip", nullable = false)
    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "user_authority")
    public String getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(String userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Column(name = "time_of_activity", nullable = false)
    public LocalDateTime getTimeOfActivity() {
        return timeOfActivity;
    }

    public void setTimeOfActivity(LocalDateTime timeOfActivity) {
        this.timeOfActivity = timeOfActivity;
    }

    @Column(name = "url", nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "activity_message", columnDefinition = "text", nullable = false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
