package com.crud.tasks.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trello {
    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;

    public Trello() {
    }

    public Trello(int board, int card) {
        this.board = board;
        this.card = card;
    }
}