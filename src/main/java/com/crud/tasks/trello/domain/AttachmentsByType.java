package com.crud.tasks.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentsByType {
    public AttachmentsByType(Trello trello) {
        this.trello = trello;
    }

    @JsonProperty("trello")
    private Trello trello;
}