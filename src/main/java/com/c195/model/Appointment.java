package com.c195.model;

import java.time.LocalDateTime;

/**
 * The Appointment class creates the blueprint for the Appointment objects.
 */

public class Appointment {

    private int appointmentId;
    private String appointmentTitle;
    private String appointmentDescription;
    private String appointmentLocation;
    private String appointmentType;
    private LocalDateTime start;
    private LocalDateTime end;
    public int ApptCustomerId;
    public int ApptUserId;
    public int ApptContactId;

    /**
     * The constructor for the Appointment objects.
     *
     * @param appointmentId Appointment ID
     * @param appointmentTitle Appointment title
     * @param appointmentDescription Appointment description
     * @param appointmentLocation Appointment Location
     * @param appointmentType Appointment type
     * @param start Appointment start time and date
     * @param end Appointment end time and date
     * @param apptCustomerId Appointment customer ID
     * @param apptUserId Appointment user ID
     * @param apptContactId Appointment contact ID
     */

    public Appointment(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType, LocalDateTime start, LocalDateTime end, int apptCustomerId, int apptUserId, int apptContactId) {
        this.appointmentId = appointmentId;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDescription = appointmentDescription;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.start = start;
        this.end = end;
        this.ApptCustomerId = apptCustomerId;
        this.ApptUserId = apptUserId;
        this.ApptContactId = apptContactId;
    }

    /**
     * Retrieves appointment ID
     *
     * @return returns appointment ID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets appointment ID.
     *
     * @param appointmentId Pass in appointment ID.
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Retrieves appointment title.
     *
     * @return appointment title
     */
    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    /**
     * Sets appointment title.
     *
     * @param appointmentTitle Appointment title
     */
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    /**
     * Retrieves appointment description
     *
     * @return appointment description
     */
    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    /**
     * Sets appointment description.
     *
     * @param appointmentDescription Appointment description
     */
    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    /**
     * Retrieves appointment location.
     *
     * @return appointment location
     */
    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    /**
     * Sets appointment location.
     *
     * @param appointmentLocation Appointment location
     */
    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    /**
     * Retrieves appointment type
     *
     * @return appointment type
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Sets appointment type.
     *
     * @param appointmentType Appointment type
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Retrieves start date and time of appointment.
     *
     * @return Start date and time
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Sets start date and time of appointment.
     *
     * @param start Start date and time
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Retrieves end date and time of appointment.
     *
     * @return End date and time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Sets end date and time of appointment.
     *
     * @param end End date and time
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Retrieves appointment customer ID.
     *
     * @return Customer ID
     */
    public int getApptCustomerId() {
        return ApptCustomerId;
    }

    /**
     * Sets appointment customer ID.
     *
     * @param apptCustomerId Appointment customer ID
     */
    public void setApptCustomerId(int apptCustomerId) {
        ApptCustomerId = apptCustomerId;
    }

    /**
     * Retrieves appointment user ID.
     *
     * @return user ID
     */
    public int getApptUserId() {
        return ApptUserId;
    }

    /**
     * Sets appointment User ID.
     *
     * @param apptUserId Appointment User ID
     */
    public void setApptUserId(int apptUserId) {
        ApptUserId = apptUserId;
    }

    /**
     * Retrieves contact ID.
     *
     * @return contact ID
     */
    public int getApptContactId() {
        return ApptContactId;
    }

    /**
     * Sets appointment contact ID.
     *
     * @param apptContactId Appointment Contact ID
     */
    public void setApptContactId(int apptContactId) {
        ApptContactId = apptContactId;
    }
}
