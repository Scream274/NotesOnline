package com.mynotes.NotesOnline.controllers;

import com.mynotes.NotesOnline.models.NotesUser;
import com.mynotes.NotesOnline.services.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithAnonymousUser
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest extends BaseControllerTest {

    @MockBean
    private UserService userService;

    @Test
    @SneakyThrows
    public void testShowRegistrationForm() {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @SneakyThrows
    public void testRegisterUserSuccess() {
        // Perform registration request
        mockMvc.perform(post("/register")
                        .param("email", "test@gmail.com")
                        .param("password", "password123")
                        .param("fullName", "Test User"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(userService).addUser(any(NotesUser.class));
    }

    @Test
    @SneakyThrows
    public void testRegisterUserValidationFailure() {
        // Perform and Assert
        mockMvc.perform(post("/register")
                        .param("email", "invalid-email") // invalid email
                        .param("password", "password123")
                        .param("fullName", "Test name"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrors("user", "email"));

        verify(userService, never()).addUser(any(NotesUser.class));
    }
}
