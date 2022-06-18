package com.lucasfroque.springdocker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasfroque.springdocker.entities.Student;
import com.lucasfroque.springdocker.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @MockBean
    private StudentService service;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper mapper;

    public static final long ID = 1L;
    public static final String NAME = "Lucas";
    public static final byte AGE = (byte) 21;
    public static final String GENDER  = "M";
    Student student;

    @BeforeEach
    void setUp() {
        start();
    }

    @Test
    void findAll() throws Exception {
        when(service.findAll()).thenReturn(List.of(student, student));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}, {}]"));
    }

    @Test
    void whenCreateThenReturnSucess() throws Exception {
        when(service.insert(any())).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION,"http://localhost/students/1"));
    }
    private void start(){
        student = new Student(ID, NAME, AGE, GENDER);
    }
}