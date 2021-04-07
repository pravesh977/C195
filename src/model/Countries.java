package model;

import java.lang.reflect.Constructor;

public class Countries {

    private int countryId;
    private String countryName;

    /** Constructor for the Countries class.*/
    public Countries(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /** Returns the country's id*/
    public int getCountryId() {
        return countryId;
    }

    /** Returns the country's name*/
    public String getCountryName() {
        return countryName;
    }

    @Override
    public String toString() {
        return getCountryName();
    }
}
