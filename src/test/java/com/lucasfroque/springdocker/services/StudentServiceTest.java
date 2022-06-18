package com.lucasfroque.springdocker.services;

import com.lucasfroque.springdocker.entities.Student;
import com.lucasfroque.springdocker.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "Lucas";
    public static final byte AGE = (byte) 21;
    public static final String GENDER  = "M";
    Student student;

    private StudentRepository repository;
    private StudentService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void insert() {
        when(repository.save(any())).thenReturn(student);
        Student response = service.insert(student);

        assertNotNull(response);
        assertEquals(Student.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(AGE, response.getAge());
        assertEquals(GENDER, response.getGender());
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(student));

        List<Student> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Student.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
        assertEquals(NAME, response.get(0).getName());
        assertEquals(AGE, response.get(0).getAge());
        assertEquals(GENDER, response.get(0).getGender());
    }

    private void start(){
        student = new Student(ID, NAME, AGE, GENDER);
    }
}