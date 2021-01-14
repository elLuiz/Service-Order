package com.works.soworks.soworksapi.domain.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
public class Commentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private ServiceOrder serviceOrder;
    @NotBlank
    private String description;
    @Column(name = "datecommentary")
    private OffsetDateTime dateCommentary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getDateCommentary() {
        return dateCommentary;
    }

    public void setDateCommentary(OffsetDateTime dateCommentary) {
        this.dateCommentary = dateCommentary;
    }
}
