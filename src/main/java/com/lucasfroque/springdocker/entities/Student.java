package com.lucasfroque.springdocker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    public static final Long serialVersion = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(nullable=false)
        private String name;
        private byte age;
        @Column(nullable=false, length=1)
        private String gender;

}
