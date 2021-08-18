package com.crud.tasks.trello.domain;

import lombok.Data;

@Data
public class TrelloCardDto {
    private final String name;
    private final String description;
    private final String pos;
    private final String listId;
}