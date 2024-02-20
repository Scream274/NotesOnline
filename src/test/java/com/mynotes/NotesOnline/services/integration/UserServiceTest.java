package com.mynotes.NotesOnline.services.integration;

import com.mynotes.NotesOnline.models.NotesUser;
import com.mynotes.NotesOnline.repositories.UserRepository;
import com.mynotes.NotesOnline.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAddUser() {
        // Prepare
        String testEmail = "user@gmail.com";
        NotesUser user = NotesUser.builder().email(testEmail).password("password123").build();

        // Act
        userService.addUser(user);

        // Assert
        NotesUser savedUser = userRepository.findByEmail(testEmail).orElse(null);
        assertNotNull(savedUser);
        assertEquals(testEmail, savedUser.getEmail());
        assertTrue(passwordEncoder.matches("password123", savedUser.getPassword()));
    }

    @Test
    public void testFindByEmail() {
        // Prepare
        String userEmail = "test@gmail.com";
        String testName = "Anatolii Omelchenko";

        // Assert
        NotesUser foundUser = userService.findByEmail(userEmail);
        assertNotNull(foundUser);
        assertEquals(testName, foundUser.getFullName());
    }

    @Test
    public void testFindByEmailNotFound() {
        // Prepare
        String userEmail = "nonexistent@gmail.com";

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.findByEmail(userEmail));
    }
}
