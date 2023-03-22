package com.c195.model;

/**
 * The Country class is the blueprint for the Country objects.
 */
public class Country {

    private int countryId;
    private String countryName;

    /**
     * This is the contructor for the Country objects.
     *
     * @param countryId Country ID
     * @param countryName Country Name
     */
    public Country(int countryId, String countryName){

        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * Retrieves Country ID.
     *
     * @return Country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets country ID.
     *
     * @param countryId Country ID
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Retrieves country name.
     *
     * @return Country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets country name.
     *
     * @param countryName Country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Overrides default toString method to return custom string representation of Country object.
     *
     * @return Custom string representation of Country object.
     */
    @Override
    public String toString(){
        return (countryName + " (ID: " +Integer.toString(countryId) + ")");
    }
}
