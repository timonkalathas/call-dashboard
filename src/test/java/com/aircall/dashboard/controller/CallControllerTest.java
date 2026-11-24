package com.aircall.dashboard.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CallControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void shouldReturnActiveCalls() throws Exception {
        mockMvc.perform(get("/calls"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calls").isArray());
    }

    @Test
    void shouldReturnCallById() throws Exception {
        mockMvc.perform(get("/calls/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void shouldReturn404WhenCallNotFound() throws Exception {
        mockMvc.perform(get("/calls/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Call not found"));
    }

    @Test
    void shouldArchiveCall() throws Exception {
        mockMvc.perform(patch("/calls/1/archive"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_archived").value(true));
    }

    @Test
    void shouldAddNoteToCall() throws Exception {
        mockMvc.perform(post("/calls/1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"Follow up next week\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("Follow up next week"));
    }

    @Test
    void shouldReturn400WhenNoteContentIsBlank() throws Exception {
        mockMvc.perform(post("/calls/1/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\": \"   \"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Content is required"));
    }
}
