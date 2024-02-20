package com.mynotes.NotesOnline.controllers;

import com.mynotes.NotesOnline.models.Note;
import com.mynotes.NotesOnline.models.NotesUser;
import com.mynotes.NotesOnline.models.enums.Priority;
import com.mynotes.NotesOnline.services.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest extends BaseControllerTest {

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(authorities = {"ADMIN", "USER"}, username = "test@gmail.com")
    @SneakyThrows
    public void testProfilePageForAuthenticatedUser() {
        // Prepare
        String userEmail = "test@gmail.com";

        NotesUser user = new NotesUser();
        user.setEmail(userEmail);

        Set<Note> notes = Set.of(
                Note.builder().text("Text - 1").title("Title - 1").priority(Priority.MEDIUM).build(),
                Note.builder().text("Text - 2").title("Title - 2").priority(Priority.LOW).build());

        user.setNotes(notes);

        when(userService.findByEmail(userEmail)).thenReturn(user);

        // Act and Assert
        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("user", "notes"))
                .andExpect(model().attribute("user", user))
                .andExpect(model().attribute("notes", hasSize(2)));

        verify(userService).findByEmail(userEmail);
    }

    @Test
    @WithAnonymousUser
    @SneakyThrows
    public void testProfilePageForUnauthenticatedUser() {
        // Act and Assert
        mockMvc.perform(get("/profile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));

        verify(userService, never()).findByEmail(anyString());
    }

}
