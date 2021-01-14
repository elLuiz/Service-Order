package com.works.soworks.soworksapi.model;

import javax.validation.constraints.NotBlank;

public class CommentaryInputModel {
    @NotBlank
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
