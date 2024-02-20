package com.mynotes.NotesOnline.controllers;

import com.mynotes.NotesOnline.config.CustomUserDetailService;
import com.mynotes.NotesOnline.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@Import(WebSecurityConfig.class)
@WebMvcTest
public abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected CustomUserDetailService userDetailsService;
}
