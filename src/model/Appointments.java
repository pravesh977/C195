package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Appointments Class that is used to create and manipulate the Appointments objects.
 */
public class Appointments {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerId;
    private String customerName;
    private int userId;
    private String userName;
    private int contactId;
    private String contactName;

    //added
    private String month;
    private int occurrences;

    /**
     * added new constructor overloading for reports type and month.
     */
    public Appointments(String month, String type, int occurrences) {
        this.month = month;
        this.type = type;
        this.occurrences = occurrences;
    }

    /**
     * added constructor overloading for contact schedule report.
     */
    public Appointments(int appointmentId, String title, String description, String type, LocalDateTime startTime, LocalDateTime endTime, int customerId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.contactId = contactId;
    }

    /**
     * Full constructor needed for the table views.
     */
    public Appointments(int appointmentId, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, int customerId, String customerName, int userId, String userName, int contactId, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.customerName = customerName;
        this.userId = userId;
        this.userName = userName;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**
     * Returns month
     */
    public String getMonth() {
        return month;
    }

    /**
     * Returns occurrences
     */
    public int getOccurrences() {
        return occurrences;
    }

    /**
     * Returns appointmentId
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Returns title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets startTime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets endTime
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Returns userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Returns contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Returns customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Returns userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}


