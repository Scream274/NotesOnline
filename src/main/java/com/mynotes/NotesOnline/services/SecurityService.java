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
        boolean res = note.getUser() != null && note.getUser().getEmail().equals(userEmail);

        System.out.println("id: " + noteId);
        System.out.println("email: " + userEmail);
        System.out.println("res: " + res);

        return res;
    }
}
