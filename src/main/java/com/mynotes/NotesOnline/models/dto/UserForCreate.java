package com.mynotes.NotesOnline.models.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserForCreate {

    private String fullName;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Email cant be empty")
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password cant be empty")
    private String password;
}
