package com.works.soworks.soworksapi.model;

import com.works.soworks.soworksapi.domain.model.Status;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ServiceOrderModel {
    private Long id;
    private ClientResumeModel client;
    private String description;
    private BigDecimal price;
    private Status status;
    private OffsetDateTime date_opening;
    private OffsetDateTime date_finish;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OffsetDateTime getDate_opening() {
        return date_opening;
    }

    public void setDate_opening(OffsetDateTime date_opening) {
        this.date_opening = date_opening;
    }

    public OffsetDateTime getDate_finish() {
        return date_finish;
    }

    public void setDate_finish(OffsetDateTime date_finish) {
        this.date_finish = date_finish;
    }

    public ClientResumeModel getClient() {
        return client;
    }

    public void setClient(ClientResumeModel client) {
        this.client = client;
    }
}
