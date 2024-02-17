package com.mynotes.NotesOnline.repositories;

import com.mynotes.NotesOnline.models.NotesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<NotesUser, Long> {
    Optional<NotesUser> findByEmail(String email);
}