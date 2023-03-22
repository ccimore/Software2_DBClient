package com.c195.model;


/**
 * The Contact class is the blueprint for the contact objects.
 */
public class Contact {

    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * This is the constructor for the Contact objects.
     *
     * @param contactId Contact ID
     * @param contactName Contact name
     * @param contactEmail Contact email
     */
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /**
     * Retrieves contact ID.
     *
     * @return Contact ID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets Contact ID
     *
     * @param contactId Contact ID
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Retrieves contact name.
     *
     * @return Contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets contact name.
     *
     * @param contactName Contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Retrieves contact email address.
     *
     * @return Contact email address
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets contact email address.
     *
     * @param contactEmail Contact email address
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Overrides default toString method to return custom string representation of Contact object.
     *
     * @return Custom string representation of Contact object.
     */
    @Override
    public String toString(){
        return (contactName + " (ID: " +Integer.toString(contactId) + ")");
    }
}
