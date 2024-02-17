package com.mynotes.NotesOnline.models;

import com.mynotes.NotesOnline.models.enums.Priority;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    private String title;

    private String text;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}
