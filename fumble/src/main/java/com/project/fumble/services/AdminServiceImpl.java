package com.project.fumble.services;

import org.springframework.stereotype.Service;

import com.project.fumble.interfaces.AdminService;
import com.project.fumble.interfaces.UserService;
import com.project.fumble.models.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;

    public AdminServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public int getTotalUserCount() {
        return userService.getAllUsers().size();
    }

    @Override
    public int getMatchedUserCount() {
        return (int) userService.getAllUsers().stream()
                .filter(user -> !user.getMatchedProfiles().isEmpty())
                .count();
    }

    @Override
    public List<User> getTopUsersWithHighestMatches(int topN) {
        return userService.getAllUsers().stream()
                .sorted((u1, u2) -> Integer.compare(u2.getMatchedProfiles().size(), u1.getMatchedProfiles().size()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getUserCohortByGender() {
        return userService.getAllUsers().stream()
                .collect(Collectors.groupingBy(User::getGender, Collectors.counting()));
    }

    @Override
    public Map<String, Long> getUserCohortByAgeGroup() {
        return userService.getAllUsers().stream()
                .collect(Collectors.groupingBy(user -> getAgeGroup(user.getAge()), Collectors.counting()));
    }

    private String getAgeGroup(int age) {
        if (age >= 18 && age <= 24) return "18-24";
        else if (age >= 25 && age <= 34) return "25-34";
        else if (age >= 35 && age <= 44) return "35-44";
        else return "45+";
    }
}