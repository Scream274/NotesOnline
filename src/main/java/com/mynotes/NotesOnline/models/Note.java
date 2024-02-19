package com.mynotes.NotesOnline.models;

import com.mynotes.NotesOnline.models.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@Table(name = "notes")
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    @Size(min = 3, max = 32, message = "Title must be between 3 and 32 characters")
    private String title;

    @Size(min = 3, max = 500, message = "Text must be between 3 and 500 characters")
    private String text;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private NotesUser user;

    private LocalDateTime date;
}
