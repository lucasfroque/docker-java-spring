package com.lucasfroque.springdocker.repositories;

import com.lucasfroque.springdocker.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
