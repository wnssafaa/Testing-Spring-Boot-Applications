package com.example.personnes.service;

import com.example.personnes.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentServiceInterface {
    String CreatStudent(Student s);
    String UpdateStudent(Student S);
    String delete(long id);
    List<Student> list();
    Optional<Student> studentById(long id);
}
