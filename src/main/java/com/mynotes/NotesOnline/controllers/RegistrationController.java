package com.mynotes.NotesOnline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mynotes.NotesOnline.models.NotesUser;
import com.mynotes.NotesOnline.models.dto.UserForCreate;
import com.mynotes.NotesOnline.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new NotesUser());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserForCreate user, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        NotesUser notesUser = objectMapper.convertValue(user, NotesUser.class);
        userService.addUser(notesUser);
        return "redirect:/";
    }
}