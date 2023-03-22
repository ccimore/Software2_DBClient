package com.c195.model;

/**
 * The FirstLevelDivision class creates the blueprint for the FirstLevelDivision objects.
 */
public class FirstLevelDivision {

    private int firstLevelDivisionId;
    private String division;
    public int FLDivCountryId;

    /**
     * The constructor for the FirstLevelDivision objects.
     *
     * @param FirstLevelDivisionId First Level Division ID
     * @param division Division
     * @param FLDivCountryId First Level Division Country ID
     */
    public FirstLevelDivision(int FirstLevelDivisionId, String division, int FLDivCountryId) {
        this.firstLevelDivisionId = FirstLevelDivisionId;
        this.division = division;
        this.FLDivCountryId = FLDivCountryId;
    }

    /**
     * Retrieves First Level Division ID.
     *
     * @return Division ID
     */
    public int getFirstLevelDivisionId() {
        return firstLevelDivisionId;
    }

    /**
     * Sets Division ID
     *
     * @param firstLevelDivisionId Division ID
     */
    public void setFirstLevelDivisionId(int firstLevelDivisionId) {
        this.firstLevelDivisionId = firstLevelDivisionId;
    }

    /**
     * Retrieves division ID
     *
     * @return Division ID
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets division name
     *
     * @param division Division name
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Retrieves First Level Division Country ID.
     *
     * @return Division country ID
     */
    public int getFLDivCountryId() {
        return FLDivCountryId;
    }

    /**
     * Sets division country ID
     *
     * @param FLDivCountryId Division country ID
     */
    public void setFLDivCountryId(int FLDivCountryId) {
        this.FLDivCountryId = FLDivCountryId;
    }

    /**
     * Overrides default toString method to return custom string representation of FirstLevelDivsion object.
     *
     * @return Custom string representation of FirstLevelDivision object.
     */
    @Override
    public String toString(){
        return (division + " (ID: " +Integer.toString(firstLevelDivisionId) + ")");
    }

}
