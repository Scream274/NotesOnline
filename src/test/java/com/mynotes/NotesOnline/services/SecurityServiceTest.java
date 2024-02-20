package com.mynotes.NotesOnline.services;

import com.mynotes.NotesOnline.models.Note;
import com.mynotes.NotesOnline.models.NotesUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
public class SecurityServiceTest {

    @MockBean
    private NotesService notesService;

    @Autowired
    private SecurityService securityService;

    @Test
    public void testIsNoteOwnerWhenUserIsNotOwner() {
        Long noteId = 1L;
        String userEmail = "test@gmail.com";

        Note note = new Note();
        note.setId(noteId);
        note.setUser(null);

        when(notesService.get(noteId)).thenReturn(note);

        assertFalse(securityService.isNoteOwner(noteId, userEmail));
    }

    @Test
    public void testIsNoteOwnerWhenUserIsOwner() {
        Long noteId = 1L;
        String userEmail = "test@gmail.com";

        Note note = new Note();
        note.setId(noteId);
        note.setUser(NotesUser.builder().email(userEmail).build());

        when(notesService.get(noteId)).thenReturn(note);

        assertTrue(securityService.isNoteOwner(noteId, userEmail));
    }
}
