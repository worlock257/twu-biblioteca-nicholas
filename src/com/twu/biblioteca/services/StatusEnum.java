package com.twu.biblioteca.services;

public enum StatusEnum {

    AVAILABLE("Available"),
    UNAVAILABLE("Unavailable");

    private String description;

    private StatusEnum(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}