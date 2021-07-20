package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    private String welcome() {
        return "welcome";
    }

    @GetMapping("/user")
    private String welcomeUser() {
        return "Welcome user";
    }

}


