package com.mynotes.NotesOnline.models.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForCreate {

    @Size(min = 3, max = 32, message = "Name must be between 3 and 32 characters")
    @NotBlank(message = "Name cant be empty")
    private String fullName;

    @Column(length = 100, unique = true)
    @NotBlank(message = "Email cant be empty")
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password cant be empty")
    @Size(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    private String password;
}
