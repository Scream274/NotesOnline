package com.mynotes.NotesOnline.services.impl;

import com.mynotes.NotesOnline.models.Note;
import com.mynotes.NotesOnline.repositories.NotesRepository;
import com.mynotes.NotesOnline.services.NotesService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.mynotes.NotesOnline.models.enums.Priority.LOW;
import static com.mynotes.NotesOnline.models.enums.Priority.MEDIUM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
public class NotesServiceImplTest {

    @MockBean
    private NotesRepository notesRepository;

    @Autowired
    private NotesService notesService;

    private Note testNote;

    @BeforeEach
    public void setUp() {
        testNote = new Note();
        testNote.setId(1L);
        testNote.setTitle("Test Title");
        testNote.setText("Test Text");
        testNote.setPriority(MEDIUM);
        testNote.setDate(LocalDateTime.now());
    }

    @Test
    public void testRemoveNote() {
        long noteId = 21L;
        doNothing().when(notesRepository).deleteById(noteId);

        notesService.remove(noteId);

        verify(notesRepository).deleteById(noteId);
    }

    @Test
    public void testUpdateNote() {
        long noteId = 1L;
        Note updatedNote = new Note();
        updatedNote.setTitle("Updated Title");
        updatedNote.setText("Updated Text");
        updatedNote.setPriority(LOW);
        updatedNote.setDate(LocalDateTime.now());

        when(notesRepository.findById(noteId)).thenReturn(Optional.of(testNote));
        when(notesRepository.save(any())).thenReturn(updatedNote);

        Note result = notesService.update(noteId, updatedNote);

        assertNotNull(result);
        assertEquals(updatedNote.getTitle(), result.getTitle());
        assertEquals(updatedNote.getText(), result.getText());
        assertEquals(updatedNote.getPriority(), result.getPriority());
        assertEquals(updatedNote.getDate(), result.getDate());

        verify(notesRepository).findById(noteId);
        verify(notesRepository).save(any(Note.class));
    }

    @Test
    public void testUpdateNoteNotFound() {
        long noteId = 1L;
        when(notesRepository.findById(noteId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> notesService.update(noteId, new Note()));

        verify(notesRepository).findById(noteId);
        verify(notesRepository, never()).save(any(Note.class));
    }

    @Test
    public void testGetNote() {
        long noteId = 1L;
        when(notesRepository.findById(noteId)).thenReturn(Optional.of(testNote));

        Note result = notesService.get(noteId);

        assertNotNull(result);
        assertEquals(testNote.getTitle(), result.getTitle());
        assertEquals(testNote.getText(), result.getText());
        assertEquals(testNote.getPriority(), result.getPriority());
        assertEquals(testNote.getDate(), result.getDate());

        verify(notesRepository).findById(noteId);
    }

    @Test
    public void testGetNoteNotFound() {
        long noteId = 1L;
        when(notesRepository.findById(noteId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> notesService.get(noteId));

        verify(notesRepository).findById(noteId);
    }

    @Test
    public void testSaveNote() {
        Note newNote = new Note();
        when(notesRepository.save(any())).thenReturn(newNote);

        notesService.save(newNote);

        verify(notesRepository).save(newNote);
    }
}
