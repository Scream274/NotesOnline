package com.mynotes.NotesOnline.services;

import com.mynotes.NotesOnline.models.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final NotesService notesService;

    public boolean isNoteOwner(Long noteId, String userEmail) {
        Note note = notesService.get(noteId);
        return note.getUser() != null && note.getUser().getEmail().equals(userEmail);
    }
}
