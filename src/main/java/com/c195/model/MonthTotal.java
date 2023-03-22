package com.c195.model;

/**
 * The MonthTotal class creates the blueprint for the MonthTotal objects.
 * It is a subclass of the Total class.
 */
public class MonthTotal extends Total{

    private String month;

    /**
     * The constructor for the MonthTotal objects.
     * Inherits from the Total superclass.
     *
     * @param total Total
     * @param month Month
     */
    public MonthTotal(int total, String month) {
        super(total);

        this.month = month;
    }

    /**
     * Retrieves month.
     *
     * @return Month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets month.
     *
     * @param month Month
     */
    public void setMonth(String month) {
        this.month = month;
    }


}
