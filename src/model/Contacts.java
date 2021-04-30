package model;

/**
 * Contacts Class that is used to create and manipulate the Contacts objects.
 */
public class Contacts {

    private int contactId;
    private String contactName;
    private String contactEmail;

    /**
     * Constructor for the Contacts class.
     */
    public Contacts(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
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
     * Returns contactEmail
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Sets contactEmail
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Override method that is needed to display the name of the contact on the ComboBox instead of the random object reference.
     */
    @Override
    public String toString() {
        return contactName;
    }
}
