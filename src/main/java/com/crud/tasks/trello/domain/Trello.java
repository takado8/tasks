package com.crud.tasks.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Trello {
    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;

    public Trello(int board, int card) {
        this.board = board;
        this.card = card;
    }
}