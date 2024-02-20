package com.mynotes.NotesOnline.services;

import com.mynotes.NotesOnline.models.NotesUser;
import jakarta.transaction.Transactional;

public interface UserService {
    @Transactional
    void addUser(NotesUser user);

    NotesUser findByEmail(String email);

}
