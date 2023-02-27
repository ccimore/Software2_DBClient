package com.c195.model;

public class FirstLevelDivision {

    private int FirstLevelDivisionId;
    private String Division;
    public int FLDivCountryId;

    public FirstLevelDivision(int FirstLevelDivisionId, String division, int FLDivCountryId) {
        this.FirstLevelDivisionId = FirstLevelDivisionId;
        this.Division = division;
        this.FLDivCountryId = FLDivCountryId;
    }

    public int getFirstLevelDivisionId() {
        return FirstLevelDivisionId;
    }

    public void setFirstLevelDivisionId(int firstLevelDivisionId) {
        FirstLevelDivisionId = firstLevelDivisionId;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public int getFLDivCountryId() {
        return FLDivCountryId;
    }

    public void setFLDivCountryId(int FLDivCountryId) {
        this.FLDivCountryId = FLDivCountryId;
    }
}
