package com.mynotes.NotesOnline.services.impl;

import com.mynotes.NotesOnline.models.Note;
import com.mynotes.NotesOnline.repositories.NotesRepository;
import com.mynotes.NotesOnline.services.NotesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NotesRepository notesRepository;

    @Override
    @Transactional
    public void remove(Long id) {
        notesRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Note update(Long id, Note note) {
        var currentNote = notesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note with id '" + id + "' was not found!"));

        currentNote.setText(note.getText());
        currentNote.setDate(note.getDate());
        currentNote.setPriority(note.getPriority());
        currentNote.setTitle(note.getTitle());

        return notesRepository.save(currentNote);
    }

    @Override
    public Note get(Long id) {
        return notesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note with id '" + id + "' was not found!"));
    }

    @Override
    @Transactional
    public void save(Note note) {
        notesRepository.save(note);
    }
}
