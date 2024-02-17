package com.mynotes.NotesOnline.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.mynotes.NotesOnline.models.Note;
import com.mynotes.NotesOnline.models.NotesUser;
import com.mynotes.NotesOnline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        String email = principal.getName();
        NotesUser user = userService.findByEmail(email);

        if (user != null) {
            List<Note> sortedNotes = user.getNotes().stream()
                    .sorted(Comparator.comparing(note -> note.getPriority().ordinal()))
                    .toList();
            model.addAttribute("user", user);
            model.addAttribute("notes", sortedNotes);
            return "profile";
        } else {
            return "redirect:/login";
        }
    }


}
