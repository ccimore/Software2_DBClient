package com.c195.model;

public class FirstLevelDivision {

    private int firstLevelDivisionId;
    private String division;
    public int FLDivCountryId;

    public FirstLevelDivision(int FirstLevelDivisionId, String division, int FLDivCountryId) {
        this.firstLevelDivisionId = FirstLevelDivisionId;
        this.division = division;
        this.FLDivCountryId = FLDivCountryId;
    }

    public int getFirstLevelDivisionId() {
        return firstLevelDivisionId;
    }

    public void setFirstLevelDivisionId(int firstLevelDivisionId) {
        this.firstLevelDivisionId = firstLevelDivisionId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getFLDivCountryId() {
        return FLDivCountryId;
    }

    public void setFLDivCountryId(int FLDivCountryId) {
        this.FLDivCountryId = FLDivCountryId;
    }

    @Override
    public String toString(){
        return (division + " (ID: " +Integer.toString(firstLevelDivisionId) + ")");
    }

}
