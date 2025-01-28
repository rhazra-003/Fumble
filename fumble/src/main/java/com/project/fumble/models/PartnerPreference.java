package com.project.fumble.models;

import lombok.Data;

@Data
public class PartnerPreference {
    private int minAge;
    private int maxAge;
    private String preferredGender;
    private boolean isStrictGender;

    public PartnerPreference(int minAge, int maxAge, String preferredGender, boolean isStrictGender) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.preferredGender = preferredGender;
        this.isStrictGender = isStrictGender;
    }
}