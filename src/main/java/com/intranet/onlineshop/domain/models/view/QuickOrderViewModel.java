package com.intranet.onlineshop.domain.models.view;

import com.intranet.onlineshop.domain.entities.Status;

import java.time.LocalDateTime;

public class QuickOrderViewModel {

    private String id;
    private LocalDateTime purchaseDate;
    private Status status;

    public QuickOrderViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
