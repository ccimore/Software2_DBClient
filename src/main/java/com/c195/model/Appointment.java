package com.c195.model;

import java.time.LocalDateTime;

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

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getApptCustomerId() {
        return ApptCustomerId;
    }

    public void setApptCustomerId(int apptCustomerId) {
        ApptCustomerId = apptCustomerId;
    }

    public int getApptUserId() {
        return ApptUserId;
    }

    public void setApptUserId(int apptUserId) {
        ApptUserId = apptUserId;
    }

    public int getApptContactId() {
        return ApptContactId;
    }

    public void setApptContactId(int apptContactId) {
        ApptContactId = apptContactId;
    }
}
