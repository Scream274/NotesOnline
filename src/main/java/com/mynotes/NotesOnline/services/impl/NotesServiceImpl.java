package com.mynotes.NotesOnline.services.impl;

import com.mynotes.NotesOnline.models.Note;
import com.mynotes.NotesOnline.repositories.NotesRepository;
import com.mynotes.NotesOnline.services.NotesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotesRepository notesRepository;

    @Override
    public void remove(Long id) {
        notesRepository.deleteById(id);
    }

    @Override
    public Note update(Long id, Note note) {
        var currentNote = notesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note with id '" + id + "' was not found!"));

        currentNote.setText(note.getText());
        currentNote.setPriority(note.getPriority());
        currentNote.setTitle(note.getTitle());

        return notesRepository.save(currentNote);
    }

    @Override
    public Note get(Long id) {
        return notesRepository.getReferenceById(id);
    }

    @Override
    public void save(Note note) {
        notesRepository.save(note);
    }
}
