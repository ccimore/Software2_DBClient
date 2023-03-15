package com.c195.model;

public class TypeTotal extends Total{
    private String type;

    public TypeTotal(int total, String type) {
        super(total);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
