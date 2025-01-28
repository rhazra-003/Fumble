package com.project.fumble.models;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

import com.project.fumble.config.UserIdGenerator;

@Data
public class User {
    private String id;
    private String name;
    private int age;
    private String gender;
    private List<String> interests = new ArrayList<>();
    private PartnerPreference partnerPreference;
    private List<User> matchedProfiles = new ArrayList<>();
    private List<String> acceptedProfiles = new ArrayList<>();
    private List<String> declinedProfiles = new ArrayList<>();
    private List<String> superAcceptedProfiles = new ArrayList<>();
    private int boostCount;
    private boolean hasSuperAccepted;

    public User(String name, int age, String gender) {
        this.id = UserIdGenerator.generateUserId();
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}