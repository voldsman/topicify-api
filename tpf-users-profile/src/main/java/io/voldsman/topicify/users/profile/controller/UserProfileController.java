package io.voldsman.topicify.users.profile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users-profile")
public class UserProfileController {

    @GetMapping
    public String test() {
        return "Users working...";
    }
}
