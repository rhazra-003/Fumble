package com.project.fumble.interfaces;

import java.util.List;
import java.util.Map;

import com.project.fumble.models.User;

public interface AdminService {
    int getTotalUserCount();
    int getMatchedUserCount();
    List<User> getTopUsersWithHighestMatches(int topN);
    Map<String, Long> getUserCohortByGender();
    Map<String, Long> getUserCohortByAgeGroup();
}