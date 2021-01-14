package com.works.soworks.soworksapi.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.works.soworks.soworksapi.domain.exception.ClientException;
import com.works.soworks.soworksapi.domain.validation.ValidationGroup;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroup.ClientGroupId.class)
    @NotNull
    @ManyToOne
    private Client client;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Status status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime date_opening;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime date_finish;
    @OneToMany(mappedBy = "serviceOrder")
    private List<Commentary> commentaries = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrder that = (ServiceOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private boolean canBeClose(){
        return Status.OPEN.equals(getStatus());
    }

    public void finish() {
        if(!canBeClose()){
            throw new ClientException("Could not finish this order. Because it is: " + getStatus());
        }

        setStatus(Status.CLOSED);
        setDate_finish(OffsetDateTime.now());
    }
}
