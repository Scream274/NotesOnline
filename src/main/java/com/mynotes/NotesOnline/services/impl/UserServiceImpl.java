package com.mynotes.NotesOnline.services.impl;

import com.mynotes.NotesOnline.models.NotesUser;
import com.mynotes.NotesOnline.repositories.UserRepository;
import com.mynotes.NotesOnline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void addUser(NotesUser user) {

    }

    @Override
    public NotesUser findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' was not found!"));
    }

    @Override
    public void update(NotesUser user) {

    }
}
