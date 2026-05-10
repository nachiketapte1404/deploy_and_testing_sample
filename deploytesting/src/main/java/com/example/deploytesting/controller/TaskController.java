package com.example.deploytesting.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200") // Add this!
public class TaskController {

    @GetMapping
    public List<String> getTasks() {
        return Arrays.asList("Setup Spring Boot", "Configure JWT", "Build Angular App");
    }
}