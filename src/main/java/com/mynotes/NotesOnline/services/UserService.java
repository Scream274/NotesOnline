package com.mynotes.NotesOnline.services;

import com.mynotes.NotesOnline.models.NotesUser;

public interface UserService {

    void addUser(NotesUser user);

    NotesUser findByEmail(String email);

}
