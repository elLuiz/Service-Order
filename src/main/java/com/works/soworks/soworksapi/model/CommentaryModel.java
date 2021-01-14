package com.works.soworks.soworksapi.model;

import java.time.OffsetDateTime;

public class CommentaryModel {
    private Long id;
    private String description;
    private OffsetDateTime dateCommentary;

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

    public OffsetDateTime getDateCommentary() {
        return dateCommentary;
    }

    public void setDateCommentary(OffsetDateTime dateCommentary) {
        this.dateCommentary = dateCommentary;
    }
}
