package model;

/**
 * Users Class that is used to create and manipulate the Users objects.
 */
public class Users {

    private int userId;
    private String userName;
    private String password;

    /** Constructor for the Users Class.*/
    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /** returns userId*/
    public int getUserId() {
        return userId;
    }

    /** sets userId*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** returns userName*/
    public String getUserName() {
        return userName;
    }

    /** sets userName*/
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** returns userPassword*/
    public String getPassword() {
        return password;
    }

    /** sets password*/
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Override method that is needed to display the name of the users on the ComboBox instead of the random object reference.
     */
    @Override
    public String toString() {
        return userName;
    }
}
