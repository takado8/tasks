package com.crud.tasks.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCard {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

    @JsonProperty("badges")
    private Badges badges;

    public CreatedTrelloCard(String id, String name, String shortUrl, Badges badges) {
        this.id = id;
        this.name = name;
        this.shortUrl = shortUrl;
        this.badges = badges;
    }
}