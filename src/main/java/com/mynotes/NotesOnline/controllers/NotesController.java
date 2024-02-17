package com.mynotes.NotesOnline.controllers;

import com.mynotes.NotesOnline.models.Note;
import com.mynotes.NotesOnline.services.NotesService;
import com.mynotes.NotesOnline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller("/notes")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;
    private final UserService userService;

    @GetMapping("/edit/{noteId}")
    public String showEditForm(@PathVariable Long noteId, Model model) {
        model.addAttribute("note", notesService.get(noteId));
        return "note-edit";
    }

    @PostMapping("/update")
    public String showEditForm(@ModelAttribute("note") Note note) {
        notesService.update(note.getId(), note);
        return "redirect:/profile";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable Long noteId) {
        notesService.remove(noteId);
        return "redirect:/profile";
    }

    @GetMapping("/add")
    public String showAddNoteForm(Model model) {
        model.addAttribute("note", new Note());
        return "note-add";
    }

    @PostMapping("/add-note")
    public String addNewNote(@ModelAttribute("note") Note note, Principal principal) {
        note.setUser(userService.findByEmail(principal.getName()));

        notesService.save(note);
        return "redirect:/profile";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        Note note = notesService.get(id);

        String fileName = "[" + note.getPriority() + "]" + " - " + note.getTitle() + ".txt";

        ByteArrayResource resource = new ByteArrayResource(note.getText().getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
