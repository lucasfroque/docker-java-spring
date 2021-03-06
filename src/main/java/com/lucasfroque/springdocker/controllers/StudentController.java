package com.lucasfroque.springdocker.controllers;

import com.lucasfroque.springdocker.entities.Student;
import com.lucasfroque.springdocker.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value= "/students")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        List<Student> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }


    @PostMapping
    ResponseEntity<Student> insert(@RequestBody Student student){
        Student response = service.insert(student);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
