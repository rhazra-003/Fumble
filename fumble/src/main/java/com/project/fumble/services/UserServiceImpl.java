package com.project.fumble.services;

import org.springframework.stereotype.Service;

import com.project.fumble.exception.ValidationException;
import com.project.fumble.interfaces.UserService;
import com.project.fumble.models.PartnerPreference;
import com.project.fumble.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

    // Predefined list of interests
    private static final List<String> PREDEFINED_INTERESTS = Arrays.asList(
            "Pets", "Movies", "Travel", "Books", "Yoga", "Cooking", "Dancing", "Music",
            "Art", "Photography", "Fashion", "Reading", "Hiking", "Gardening", "Gaming",
            "Fitness", "Sports", "Technology", "Food", "Writing"
    );

    @Override
    public User createProfile(String name, int age, String gender) {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty.");
        }
        if (age < 18 || age > 100) {
            throw new ValidationException("Age must be between 18 and 100.");
        }
        if (gender == null || (!gender.equalsIgnoreCase("male") && !gender.equalsIgnoreCase("female"))) {
            throw new ValidationException("Gender must be either 'male' or 'female'.");
        }

        User user = new User(name, age, gender);
        users.add(user);

        return user;
    }

    @Override
    public void addInterests(String userId, List<String> interests) {
        User user = getUserById(userId);
        if (interests == null || interests.isEmpty()) {
            throw new ValidationException("Interests cannot be empty.");
        }
        user.setInterests(interests);
    }

    // Method to suggest interests to the user
    public List<String> suggestInterests() {
        return PREDEFINED_INTERESTS;
    }

    @Override
    public void setPartnerPreference(String userId, PartnerPreference preference) {
        User user = getUserById(userId);
        if (preference == null) {
            throw new ValidationException("Partner preference cannot be null.");
        }
        if (preference.getMinAge() < 18 || preference.getMaxAge() > 100 || preference.getMinAge() > preference.getMaxAge()) {
            throw new ValidationException("Invalid age range in partner preference.");
        }
        user.setPartnerPreference(preference);
    }

    @Override
    public User getBestProfile(String userId) {
        User user = getUserById(userId);
        List<User> availableProfiles = users.stream()
                .filter(profile -> !profile.getId().equals(userId)) // Exclude self
                .filter(profile -> !user.getDeclinedProfiles().contains(profile.getId())) // Exclude declined profiles
                .filter(profile -> !user.getAcceptedProfiles().contains(profile.getId())) // Exclude already accepted profiles
                .collect(Collectors.toList());

        if (availableProfiles.isEmpty()) {
            throw new ValidationException("No available profiles to match.");
        }

        // Use the MatchingService to rank profiles
        return new MatchingService().rankProfiles(user, availableProfiles).get(0);
    }

    @Override
    public void acceptProfile(String userId, String profileId) {
        User user = getUserById(userId);
        User profile = getUserById(profileId);

        if (userId.equals(profileId)) {
            throw new ValidationException("You cannot accept your own profile.");
        }
        if (user.getDeclinedProfiles().contains(profileId)) {
            throw new ValidationException("You have already declined this profile.");
        }
        if (user.getAcceptedProfiles().contains(profileId)) {
            throw new ValidationException("You have already accepted this profile.");
        }

        user.getAcceptedProfiles().add(profileId);
        if (profile.getAcceptedProfiles().contains(userId)) {
            user.getMatchedProfiles().add(profile);
            profile.getMatchedProfiles().add(user);
        }
    }

    @Override
    public void declineProfile(String userId, String profileId) {
        User user = getUserById(userId);

        if (userId.equals(profileId)) {
            throw new ValidationException("You cannot decline your own profile.");
        }
        if (user.getDeclinedProfiles().contains(profileId)) {
            throw new ValidationException("You have already declined this profile.");
        }
        if (user.getAcceptedProfiles().contains(profileId)) {
            throw new ValidationException("You have already accepted this profile.");
        }

        user.getDeclinedProfiles().add(profileId);
    }

    @Override
    public List<User> listMatchedProfiles(String userId) {
        User user = getUserById(userId);
        return user.getMatchedProfiles();
    }

    @Override
    public void buyBoost(String userId) {
        User user = getUserById(userId);
        user.setBoostCount(user.getBoostCount() + 1);
    }

    @Override
    public void superAcceptProfile(String userId, String profileId) {
        User user = getUserById(userId);
        User profile = getUserById(profileId);

        if (userId.equals(profileId)) {
            throw new ValidationException("You cannot super-accept your own profile.");
        }
        if (user.isHasSuperAccepted()) {
            throw new ValidationException("You can only super-accept once in your lifetime.");
        }

        user.setHasSuperAccepted(true);
        profile.getSuperAcceptedProfiles().add(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    private User getUserById(String userId) {
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new ValidationException("User not found with ID: " + userId));
    }
}