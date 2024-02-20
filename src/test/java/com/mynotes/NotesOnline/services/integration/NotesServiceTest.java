package com.mynotes.NotesOnline.services.integration;

import com.mynotes.NotesOnline.models.Note;
import com.mynotes.NotesOnline.repositories.NotesRepository;
import com.mynotes.NotesOnline.services.NotesService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static com.mynotes.NotesOnline.models.enums.Priority.MEDIUM;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NotesServiceTest {

    @Autowired
    private NotesService notesService;

    @Autowired
    private NotesRepository notesRepository;

    private Note testNote;

    @BeforeEach
    public void setUp() {
        testNote = Note.builder()
                .title("Test Title")
                .text("Test Text")
                .priority(MEDIUM)
                .date(LocalDateTime.now())
                .build();
    }

    @Test
    public void testGetNoteById() {
        Note savedNote = createAndSaveTestNote();
        Note note = notesService.get(savedNote.getId());

        assertNotNull(note);
        assertEquals(note, testNote);
    }

    @Test
    public void testSaveNote() {
        notesService.save(testNote);

        // Assert
        assertNotNull(testNote.getId());
        Note retrievedNote = notesService.get(testNote.getId());
        assertNotNull(retrievedNote);
        assertEquals(testNote, retrievedNote);
    }

    @Test
    public void testUpdateNote() {
        // Prepare
        Note savedNote = createAndSaveTestNote();
        testNote.setText("Updated text");
        testNote.setTitle("Updated title");

        // Act
        notesService.update(savedNote.getId(), testNote);

        // Assert
        Note retrievedNote = notesService.get(savedNote.getId());
        assertNotNull(retrievedNote);
        assertEquals(testNote, retrievedNote);
    }

    @Test
    public void testRemoveNote() {
        // Prepare
        Note savedNote = createAndSaveTestNote();

        // Act
        notesService.remove(savedNote.getId());

        // Assert
        assertFalse(notesRepository.existsById(savedNote.getId()));
    }

    private Note createAndSaveTestNote() {
        notesService.save(testNote);
        return testNote;
    }
}
