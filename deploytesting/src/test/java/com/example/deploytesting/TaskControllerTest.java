package com.example.deploytesting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.example.deploytesting.security.JwtUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void shouldReturnForbiddenWithoutToken() throws Exception {
        mockMvc.perform(get("/api/tasks"))
               .andExpect(status().isForbidden()); // Verifies 403
    }

    @Test
    public void shouldReturnTasksWithValidToken() throws Exception {
        // 1. Generate a real token using our utility
        String token = jwtUtils.generateToken("admin");

        // 2. Perform the request with the Authorization header
        mockMvc.perform(get("/api/tasks")
               .header("Authorization", "Bearer " + token))
               .andExpect(status().isOk()); // Verifies 200
    }
}