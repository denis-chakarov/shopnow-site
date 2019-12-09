package com.intranet.onlineshop.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class used for representing the table tickets in the database
 */
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    private String sender;
    private String title;
    private String description;
    private TicketType type;
    private LocalDateTime creationDate;

    public Ticket() {
    }

    @Column(name = "sender", nullable = false)
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", columnDefinition = "text", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    @Column(name = "creation_date")
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
