package com.mynotes.NotesOnline.services;

import com.mynotes.NotesOnline.models.Note;

public interface NotesService {

    void remove(Long id);

    Note update(Long id, Note note);

    Note get(Long id);

    void save(Note note);
}
