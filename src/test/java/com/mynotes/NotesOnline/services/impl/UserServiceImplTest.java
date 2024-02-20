package com.mynotes.NotesOnline.services.impl;

import com.mynotes.NotesOnline.models.NotesUser;
import com.mynotes.NotesOnline.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testAddUser() {
        NotesUser user = new NotesUser();
        user.setEmail("test@gmail.com");
        user.setPassword("password123");

        when(userRepository.save(user)).thenReturn(user);
        userService.addUser(user);

        verify(userRepository).save(user);
        assertTrue(passwordEncoder.matches("password123", user.getPassword()));
    }

    @Test
    public void testFindByEmail() {
        String email = "test@gmail.com";
        NotesUser user = new NotesUser();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        NotesUser result = userService.findByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());

        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testFindByEmailNotFound() {
        String email = "nonexistent@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.findByEmail(email));

        verify(userRepository).findByEmail(email);
    }
}
