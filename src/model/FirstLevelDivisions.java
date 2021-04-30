package model;

/**
 * FirstLevelDivisions Class that is used to create and manipulate the FirstLevelDivisions objects.
 */
public class FirstLevelDivisions {

    private int divisionId;
    private String divisionName;
    private int countryId;

    /**
     * Constructor for first-level division class
     */
    public FirstLevelDivisions(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * Returns divisionId
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * sets divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Returns divisionName
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * sets divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * Returns countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * sets CountryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Override method that is needed to display the name of the division on the ComboBox instead of the random object reference.
     */
    @Override
    public String toString() {
        return getDivisionName();
    }
}
