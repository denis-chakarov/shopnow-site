package com.intranet.onlineshop.domain.models.service;

import com.intranet.onlineshop.domain.entities.TicketType;

public class TicketServiceModel extends BaseServiceModel {

    private String sender;
    private String title;
    private String description;
    private TicketType type;

    public TicketServiceModel() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
}
