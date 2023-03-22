package com.c195.model;

/**
 * The TypeTotal class creates the blueprint for the TypeTotal objects.
 * It is a subclass of the Total class.
 */
public class TypeTotal extends Total{

    private String type;

    /**
     *The constructor for the TypeTotal objects.
     *Inherits from the Total superclass.
     *
     * @param total Total
     * @param type Type
     */
    public TypeTotal(int total, String type) {
        super(total);
        this.type = type;
    }

    /**
     * Retrieves appointment type.
     *
     * @return Appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets appointment type.
     *
     * @param type Appointment type
     */
    public void setType(String type) {
        this.type = type;
    }
}
