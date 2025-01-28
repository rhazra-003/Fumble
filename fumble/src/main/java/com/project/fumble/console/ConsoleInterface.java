package com.project.fumble.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.fumble.exception.ValidationException;
import com.project.fumble.interfaces.AdminService;
import com.project.fumble.interfaces.UserService;
import com.project.fumble.models.PartnerPreference;
import com.project.fumble.models.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ConsoleInterface implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                try {
                    System.out.println("\n======== Welcome to Fumble ========\nEnter command (create-profile, add-interests, set-partner-preference, get-best-profile, accept-profile, decline-profile, list-matched-profiles, buy-boost, super-accept-profile, show-stats, exit): ");
                    String command = scanner.nextLine();
                    switch (command) {
                        case "create-profile":
                            createProfile(scanner);
                            break;

                        case "add-interests":
                            addInterests(scanner);
                            break;

                        case "set-partner-preference":
                            setPartnerPreference(scanner);
                            break;

                        case "get-best-profile":
                            getBestProfile(scanner);
                            break;

                        case "accept-profile":
                            acceptProfile(scanner);
                            break;

                        case "decline-profile":
                            declineProfile(scanner);
                            break;

                        case "list-matched-profiles":
                            listMatchedProfiles(scanner);
                            break;

                        case "buy-boost":
                            buyBoost(scanner);
                            break;

                        case "super-accept-profile":
                            superAcceptProfile(scanner);
                            break;

                        case "show-stats":
                            showStats(scanner);
                            break;

                        case "exit":
                            System.out.println("Exiting application.");
                            return;

                        default:
                            System.out.println("Unknown command. Please try again.");
                    }
                } catch (ValidationException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
            }
        }
    }

    private void createProfile(Scanner scanner) {
        // Step 1: Create Profile
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your gender: ");
        String gender = scanner.nextLine();
        User user = userService.createProfile(name, age, gender);

        // Step 2: Add Interests
        addInterests(scanner, user.getId());

        // Step 3: Set Partner Preference
        setPartnerPreference(scanner, user.getId());

        System.out.println("Profile created with ID: " + user.getId());
    }

    private void addInterests(Scanner scanner) {
        System.out.println("Enter user ID: ");
        String userId = scanner.nextLine();
        addInterests(scanner, userId);
    }

    private void addInterests(Scanner scanner, String userId) {
        System.out.println("Suggested interests: ");
        List<String> suggestedInterests = userService.suggestInterests();
        for (int i = 0; i < suggestedInterests.size(); i++) {
            System.out.println((i + 1) + ". " + suggestedInterests.get(i));
        }
        System.out.println("Enter the numbers of your interests (comma-separated) at max 3: ");
        String interestsInput = scanner.nextLine();
        List<String> selectedInterests = Arrays.stream(interestsInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(index -> suggestedInterests.get(index - 1))
                .collect(Collectors.toList());
        userService.addInterests(userId, selectedInterests);
        System.out.println("Interests added successfully.");
    }

    private void setPartnerPreference(Scanner scanner) {
        System.out.println("Enter user ID: ");
        String userId = scanner.nextLine();
        setPartnerPreference(scanner, userId);
    }

    private void setPartnerPreference(Scanner scanner, String userId) {
        System.out.println("Enter minimum age for your preferred partner: ");
        int minAge = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter maximum age for your preferred partner: ");
        int maxAge = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter preferred gender: ");
        String preferredGender = scanner.nextLine();
        System.out.println("Is gender preference strict? (true/false): ");
        boolean isStrictGender = Boolean.parseBoolean(scanner.nextLine());
        PartnerPreference preference = new PartnerPreference(minAge, maxAge, preferredGender, isStrictGender);
        userService.setPartnerPreference(userId, preference);
        System.out.println("Partner preference set successfully.");
    }

    private void getBestProfile(Scanner scanner) {
        System.out.println("Enter user ID: ");
        String userId = scanner.nextLine();
        User bestProfile = userService.getBestProfile(userId);
        System.out.println("Best profile: " + bestProfile.getName() + ", Age: " + bestProfile.getAge() + ", Gender: " + bestProfile.getGender());
    }

    private void acceptProfile(Scanner scanner) {
        System.out.println("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.println("Enter profile ID to accept: ");
        String profileId = scanner.nextLine();
        userService.acceptProfile(userId, profileId);
        System.out.println("Profile accepted.");
    }

    private void declineProfile(Scanner scanner) {
        System.out.println("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.println("Enter profile ID to decline: ");
        String profileId = scanner.nextLine();
        userService.declineProfile(userId, profileId);
        System.out.println("Profile declined.");
    }

    private void listMatchedProfiles(Scanner scanner) {
        System.out.println("Enter user ID: ");
        String userId = scanner.nextLine();
        List<User> matchedProfiles = userService.listMatchedProfiles(userId);
        if (matchedProfiles.isEmpty()) {
            System.out.println("No matched profiles.");
        } else {
            System.out.println("Matched profiles:");
            matchedProfiles.forEach(profile -> System.out.println(profile.getName() + ", Age: " + profile.getAge() + ", Gender: " + profile.getGender()));
        }
    }

    private void buyBoost(Scanner scanner) {
        System.out.println("Enter user ID: ");
        String userId = scanner.nextLine();
        userService.buyBoost(userId);
        System.out.println("Boost purchased successfully.");
    }

    private void superAcceptProfile(Scanner scanner) {
        System.out.println("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.println("Enter profile ID to super-accept: ");
        String profileId = scanner.nextLine();
        userService.superAcceptProfile(userId, profileId);
        System.out.println("Profile super-accepted.");
    }

    private void showStats(Scanner scanner) {
        System.out.println("Enter report type (total-users, matched-users, top-users, gender-cohort, age-cohort): ");
        String reportType = scanner.nextLine();
        switch (reportType) {
            case "total-users":
                System.out.println("Total users: " + adminService.getTotalUserCount());
                break;
            case "matched-users":
                System.out.println("Matched users: " + adminService.getMatchedUserCount());
                break;
            case "top-users":
                System.out.println("Enter number of top users to display: ");
                int topN = Integer.parseInt(scanner.nextLine());
                List<User> topUsers = adminService.getTopUsersWithHighestMatches(topN);
                System.out.println("Top " + topN + " users with highest matches:");
                topUsers.forEach(u -> System.out.println(u.getName() + " - " + u.getMatchedProfiles().size() + " matches"));
                break;
            case "gender-cohort":
                Map<String, Long> genderCohort = adminService.getUserCohortByGender();
                System.out.println("User cohort by gender:");
                genderCohort.forEach((g, count) -> System.out.println(g + ": " + count));
                break;
            case "age-cohort":
                Map<String, Long> ageCohort = adminService.getUserCohortByAgeGroup();
                System.out.println("User cohort by age group:");
                ageCohort.forEach((ageGroup, count) -> System.out.println(ageGroup + ": " + count));
                break;
            default:
                System.out.println("Unknown report type.");
        }
    }
}