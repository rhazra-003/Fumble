package com.project.fumble.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.project.fumble.models.PartnerPreference;
import com.project.fumble.models.User;

public class MatchingService {

    public List<User> rankProfiles(User user, List<User> allUsers) {
        return allUsers.stream()
            .filter(profile -> !profile.getId().equals(user.getId()))
            .sorted(Comparator.comparingInt(profile -> calculateRank(user, profile)))
            .collect(Collectors.toList());
    }

    private int calculateRank(User user, User profile) {
        int rank = 0;
        if (isPreferredProfile(user, profile)) {
            rank += 1000;
        }
        rank += countMutualInterests(user, profile);
        return rank;
    }

    private boolean isPreferredProfile(User user, User profile) {
        PartnerPreference preference = user.getPartnerPreference();
        return (preference.getPreferredGender().equals(profile.getGender()) || !preference.isStrictGender()) &&
               profile.getAge() >= preference.getMinAge() &&
               profile.getAge() <= preference.getMaxAge();
    }

    private int countMutualInterests(User user, User profile) {
        return (int) user.getInterests().stream()
            .filter(interest -> profile.getInterests().contains(interest))
            .count();
    }
}