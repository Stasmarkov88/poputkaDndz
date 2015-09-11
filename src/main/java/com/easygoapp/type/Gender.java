package com.easygoapp.type;

/**
 * Created by Padonag on 24.02.2015.
 */
public enum Gender {
    MALE("Mr. "),
    FEMALE("Mrs. ");

    private final String genderPrefix;

    Gender(String prefix) {
        this.genderPrefix = prefix;
    }

    public String getGenderPrefix() {
        return genderPrefix;
    }
}
