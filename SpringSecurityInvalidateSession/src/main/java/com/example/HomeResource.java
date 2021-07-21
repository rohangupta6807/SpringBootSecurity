package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    private String welcomeMessage() {
        return "Welcome";
    }

    @GetMapping("/user")
    private String welcomeUser() {
        return "Welcome user";
    }

    @GetMapping("/admin")
    private String welcomeAdmin() {
        return "Welcome Admin";
    }

}
