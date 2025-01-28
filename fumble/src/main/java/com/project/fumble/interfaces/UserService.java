package com.project.fumble.interfaces;

import java.util.List;

import com.project.fumble.models.PartnerPreference;
import com.project.fumble.models.User;

public interface UserService {
    User createProfile(String name, int age, String gender);
    void addInterests(String userId, List<String> interests);
    List<String> suggestInterests();
    void setPartnerPreference(String userId, PartnerPreference preference);
    User getBestProfile(String userId);
    void acceptProfile(String userId, String profileId);
    void declineProfile(String userId, String profileId);
    List<User> listMatchedProfiles(String userId);
    void buyBoost(String userId);
    void superAcceptProfile(String userId, String profileId);
    List<User> getAllUsers();
}