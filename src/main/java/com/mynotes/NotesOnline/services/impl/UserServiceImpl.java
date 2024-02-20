package com.mynotes.NotesOnline.services.impl;

import com.mynotes.NotesOnline.models.NotesUser;
import com.mynotes.NotesOnline.repositories.UserRepository;
import com.mynotes.NotesOnline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void addUser(NotesUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public NotesUser findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' was not found!"));
    }

}
