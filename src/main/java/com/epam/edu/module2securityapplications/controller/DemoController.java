package com.epam.edu.module2securityapplications.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/about")
    public String getAbout() {
        return "ABOUT ENDPOINT";
    }

    @GetMapping("/info")
    public String getInfo() {
        return "MVC APPLICATION";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "Hello Admin";
    }
}
