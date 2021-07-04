package com.crud.tasks.controller;

public class BoardsEmptyException extends Exception {
    @Override
    public String getMessage() {
        return "BoardsEmptyException: " + super.getMessage();
    }
}
