package com.lucasfroque.springdocker.services;

import com.lucasfroque.springdocker.repositories.StudentRepository;
import com.lucasfroque.springdocker.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public Student insert(Student student){
        repository.save(student);
        return student;
    }

    public List<Student> findAll(){
        return repository.findAll();
    }
}
