package com.c195.model;

/**
 * The Total class is an abstract superclass that the Total subclasses inherit from.
 */
public abstract class Total {

    private int total;

    /**
     * The constructor for the Total class.
     * It is the superclass constructor for the Total subclasses.
     *
     * @param total Total
     */
    public Total(int total) {
        this.total = total;
    }

    /**
     *
     * @return
     */
    public int getTotal() {
        return total;
    }

    /**
     *
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }


}
