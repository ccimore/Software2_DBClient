package com.c195.model;

public class MonthTotal extends Total{

    private String month;

    public MonthTotal(int total, String month) {
        super(total);

        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


}
