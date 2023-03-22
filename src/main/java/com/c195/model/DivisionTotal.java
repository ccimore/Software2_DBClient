package com.c195.model;

/**
 * The DivisionTotal class creates the blueprint for the DivisionTotal objects.
 * It is a subclass of the Total class.
 */
public class DivisionTotal extends Total{

    String divisionName;
    int divisionID;

    /**
     * The constructor for the DivisionTotal objects.
     * Inherits from the Total superclass.
     *
     * @param total Total
     * @param divisionName Division Name
     * @param divisionID Divsion ID
     */
    public DivisionTotal(int total, String divisionName, int divisionID) {
        super(total);

        this.divisionName = divisionName;
        this.divisionID = divisionID;
    }

    /**
     * Retrieves division name.
     *
     * @return Division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Sets division name.
     *
     * @param divisionName Division name
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Retrieves division ID
     *
     * @return Division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Sets division ID.
     *
     * @param divisionID Division ID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}
