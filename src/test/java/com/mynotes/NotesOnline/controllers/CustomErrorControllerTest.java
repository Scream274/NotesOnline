package com.mynotes.NotesOnline.controllers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static jakarta.servlet.RequestDispatcher.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomErrorController.class)
@WithMockUser(authorities = {"ADMIN", "USER"}, username = "test@gmail.com")
public class CustomErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void testHandleErrorNotFound() {
        mockMvc.perform(get("/error")
                        .requestAttr(ERROR_STATUS_CODE, NOT_FOUND.value()))
                .andExpect(status().isOk())
                .andExpect(view().name("404_page"));
    }

    @Test
    @SneakyThrows
    public void testHandleErrorForbidden() {
        mockMvc.perform(get("/error")
                        .requestAttr(ERROR_STATUS_CODE, FORBIDDEN.value()))
                .andExpect(status().isOk())
                .andExpect(view().name("access_denied_page"));
    }

    @Test
    @SneakyThrows
    public void testHandleServerError() {
        mockMvc.perform(get("/error")
                        .requestAttr(ERROR_STATUS_CODE, INTERNAL_SERVER_ERROR.value()))
                .andExpect(status().isOk())
                .andExpect(view().name("500_page"));
    }
}
