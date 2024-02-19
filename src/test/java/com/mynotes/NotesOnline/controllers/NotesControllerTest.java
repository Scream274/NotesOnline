package com.mynotes.NotesOnline.controllers;

import com.mynotes.NotesOnline.config.CustomUserDetailService;
import com.mynotes.NotesOnline.config.WebSecurityConfig;
import com.mynotes.NotesOnline.models.Note;
import com.mynotes.NotesOnline.models.NotesUser;
import com.mynotes.NotesOnline.models.enums.Priority;
import com.mynotes.NotesOnline.services.NotesService;
import com.mynotes.NotesOnline.services.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(authorities = {"ADMIN", "USER"})
@Import(WebSecurityConfig.class)
@WebMvcTest(NotesController.class)
public class NotesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotesService notesService;

    @MockBean
    private UserService userService;

    @MockBean
    private CustomUserDetailService userDetailsService;
    @Test
    @SneakyThrows
    public void testShowEditForm() {
        long noteId = 1L;
        Note note = new Note();
        when(notesService.get(noteId)).thenReturn(note);

        mockMvc.perform(get("/notes/edit/{noteId}", noteId))
                .andExpect(status().isOk())
                .andExpect(view().name("note-edit"))
                .andExpect(model().attributeExists("note"))
                .andExpect(model().attribute("note", note));
    }

    @Test
    @SneakyThrows
    public void testShowAddNoteForm() {
        mockMvc.perform(get("/notes/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("note-add"))
                .andExpect(model().attributeExists("note"));
    }

    @Test
    @SneakyThrows
    public void testAddNewNote() {
        when(userService.findByEmail(any())).thenReturn(new NotesUser());
        mockMvc.perform(post("/notes/add-note")
                .contentType(MediaType.APPLICATION_JSON)
                .param("title", "Test Title")
                .param("text", "Test Text")
                .param("priority", "LOW")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));

        verify(notesService).save(any());
    }

    @Test
    @SneakyThrows
    public void testDownloadFile() {
        long noteId = 1L;
        Note note = Note.builder()
                .title("Test")
                .text("text")
                .date(LocalDateTime.parse("2024-02-19T18:09"))
                .priority(Priority.MEDIUM)
                .build();
        when(notesService.get(noteId)).thenReturn(note);

        mockMvc.perform(get("/notes/download/{id}", noteId))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment;filename=[MEDIUM] - Test - [2024-02-19T18:09].txt"))
                .andExpect(content().string(note.getText()));
    }

    @Test
    @SneakyThrows
    public void testUpdateNote() {
        long noteId = 1L;
        Note existingNote = new Note();
        existingNote.setId(noteId);

        when(notesService.get(noteId)).thenReturn(existingNote);

        mockMvc.perform(post("/notes/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", String.valueOf(noteId))
                        .param("title", "Updated Title")
                        .param("text", "Updated Text")
                        .param("priority", "LOW")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));

        verify(notesService).update(eq(noteId), any());
    }

    @Test
    @SneakyThrows
    public void testDeleteNote() {
        long noteId = 1L;

        mockMvc.perform(get("/notes/delete/{noteId}", noteId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"));

        verify(notesService).remove(eq(noteId));
    }
}
