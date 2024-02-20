package com.mynotes.NotesOnline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mynotes.NotesOnline")
public class NotesOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesOnlineApplication.class, args);
	}

}
