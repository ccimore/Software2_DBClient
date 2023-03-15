package com.c195.model;

public class DivisionTotal extends Total{

    String divisionName;
    int divisionID;
    public DivisionTotal(int total, String divisionName, int divisionID) {
        super(total);

        this.divisionName = divisionName;
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
