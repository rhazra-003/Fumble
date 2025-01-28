package com.project.fumble.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.fumble.interfaces.UserService;
import com.project.fumble.models.PartnerPreference;
import com.project.fumble.models.User;

import java.util.List;

@Configuration
public class SampleDataLoader {

    @Bean
    public CommandLineRunner demo(UserService userService) {
        return (args) -> {
            // Female Users (13)
            User user1 = userService.createProfile("Alice", 25, "Female");
            userService.addInterests(user1.getId(), List.of("Pets", "Movies", "Travel"));
            userService.setPartnerPreference(user1.getId(), new PartnerPreference(22, 30, "Male", true));

            User user2 = userService.createProfile("Emma", 27, "Female");
            userService.addInterests(user2.getId(), List.of("Books", "Yoga", "Cooking"));
            userService.setPartnerPreference(user2.getId(), new PartnerPreference(25, 35, "Male", false));

            User user3 = userService.createProfile("Sophia", 23, "Female");
            userService.addInterests(user3.getId(), List.of("Dancing", "Music", "Art"));
            userService.setPartnerPreference(user3.getId(), new PartnerPreference(20, 28, "Male", true));

            User user4 = userService.createProfile("Olivia", 29, "Female");
            userService.addInterests(user4.getId(), List.of("Photography", "Travel", "Fashion"));
            userService.setPartnerPreference(user4.getId(), new PartnerPreference(26, 34, "Male", false));

            User user5 = userService.createProfile("Ava", 24, "Female");
            userService.addInterests(user5.getId(), List.of("Movies", "Reading", "Hiking"));
            userService.setPartnerPreference(user5.getId(), new PartnerPreference(22, 30, "Male", true));

            User user6 = userService.createProfile("Isabella", 26, "Female");
            userService.addInterests(user6.getId(), List.of("Cooking", "Gardening", "Pets"));
            userService.setPartnerPreference(user6.getId(), new PartnerPreference(24, 32, "Male", false));

            User user7 = userService.createProfile("Mia", 28, "Female");
            userService.addInterests(user7.getId(), List.of("Travel", "Music", "Dancing"));
            userService.setPartnerPreference(user7.getId(), new PartnerPreference(25, 35, "Male", true));

            User user8 = userService.createProfile("Amelia", 22, "Female");
            userService.addInterests(user8.getId(), List.of("Art", "Movies", "Reading"));
            userService.setPartnerPreference(user8.getId(), new PartnerPreference(20, 28, "Male", false));

            User user9 = userService.createProfile("Charlotte", 30, "Female");
            userService.addInterests(user9.getId(), List.of("Yoga", "Cooking", "Travel"));
            userService.setPartnerPreference(user9.getId(), new PartnerPreference(28, 36, "Male", true));

            User user10 = userService.createProfile("Ella", 21, "Female");
            userService.addInterests(user10.getId(), List.of("Music", "Dancing", "Fashion"));
            userService.setPartnerPreference(user10.getId(), new PartnerPreference(19, 27, "Male", false));

            User user11 = userService.createProfile("Grace", 27, "Female");
            userService.addInterests(user11.getId(), List.of("Reading", "Hiking", "Pets"));
            userService.setPartnerPreference(user11.getId(), new PartnerPreference(24, 32, "Male", true));

            User user12 = userService.createProfile("Lily", 26, "Female");
            userService.addInterests(user12.getId(), List.of("Cooking", "Gardening", "Movies"));
            userService.setPartnerPreference(user12.getId(), new PartnerPreference(23, 31, "Male", false));

            User user13 = userService.createProfile("Chloe", 29, "Female");
            userService.addInterests(user13.getId(), List.of("Travel", "Photography", "Art"));
            userService.setPartnerPreference(user13.getId(), new PartnerPreference(26, 34, "Male", true));

            // Male Users (12)
            User user14 = userService.createProfile("Bob", 28, "Male");
            userService.addInterests(user14.getId(), List.of("Football", "Books", "Gaming"));
            userService.setPartnerPreference(user14.getId(), new PartnerPreference(24, 32, "Female", false));

            User user15 = userService.createProfile("John", 30, "Male");
            userService.addInterests(user15.getId(), List.of("Movies", "Travel", "Cooking"));
            userService.setPartnerPreference(user15.getId(), new PartnerPreference(25, 35, "Female", true));

            User user16 = userService.createProfile("Michael", 26, "Male");
            userService.addInterests(user16.getId(), List.of("Music", "Dancing", "Fitness"));
            userService.setPartnerPreference(user16.getId(), new PartnerPreference(22, 30, "Female", false));

            User user17 = userService.createProfile("David", 27, "Male");
            userService.addInterests(user17.getId(), List.of("Reading", "Hiking", "Photography"));
            userService.setPartnerPreference(user17.getId(), new PartnerPreference(24, 32, "Female", true));

            User user18 = userService.createProfile("James", 29, "Male");
            userService.addInterests(user18.getId(), List.of("Gaming", "Movies", "Travel"));
            userService.setPartnerPreference(user18.getId(), new PartnerPreference(26, 34, "Female", false));

            User user19 = userService.createProfile("William", 25, "Male");
            userService.addInterests(user19.getId(), List.of("Cooking", "Gardening", "Pets"));
            userService.setPartnerPreference(user19.getId(), new PartnerPreference(22, 30, "Female", true));

            User user20 = userService.createProfile("Daniel", 31, "Male");
            userService.addInterests(user20.getId(), List.of("Fitness", "Music", "Art"));
            userService.setPartnerPreference(user20.getId(), new PartnerPreference(28, 36, "Female", false));

            User user21 = userService.createProfile("Matthew", 24, "Male");
            userService.addInterests(user21.getId(), List.of("Reading", "Hiking", "Movies"));
            userService.setPartnerPreference(user21.getId(), new PartnerPreference(21, 29, "Female", true));

            User user22 = userService.createProfile("Joseph", 27, "Male");
            userService.addInterests(user22.getId(), List.of("Travel", "Photography", "Cooking"));
            userService.setPartnerPreference(user22.getId(), new PartnerPreference(24, 32, "Female", false));

            User user23 = userService.createProfile("Andrew", 28, "Male");
            userService.addInterests(user23.getId(), List.of("Gaming", "Movies", "Fitness"));
            userService.setPartnerPreference(user23.getId(), new PartnerPreference(25, 35, "Female", true));

            User user24 = userService.createProfile("Ryan", 26, "Male");
            userService.addInterests(user24.getId(), List.of("Music", "Dancing", "Art"));
            userService.setPartnerPreference(user24.getId(), new PartnerPreference(23, 31, "Female", false));

            User user25 = userService.createProfile("Noah", 29, "Male");
            userService.addInterests(user25.getId(), List.of("Reading", "Hiking", "Travel"));
            userService.setPartnerPreference(user25.getId(), new PartnerPreference(26, 34, "Female", true));
        };
    }
}