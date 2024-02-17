package com.mynotes.NotesOnline.services;

import com.mynotes.NotesOnline.models.Note;
import org.springframework.transaction.annotation.Transactional;

public interface NotesService {

    @Transactional
    void remove(Long id);

    @Transactional
    Note update(Long id, Note note);

    Note get(Long id);

    void save(Note note);
}
