package com.example.personnes.service;

import com.example.personnes.model.Student;
import com.example.personnes.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentServiceInterface{
     private final StudentRepo Repo;

    public StudentService(StudentRepo repo) {
        Repo = repo;
    }

    @Override
    public String CreatStudent(Student s) {
       Repo.save(s);
       return "student add Successfully";
    }

    @Override
    public String UpdateStudent(Student S) {
        Optional<Student> find = Repo.findById(S.getId());
        if (find.isPresent()) {
            Student A = find.get(); // Utiliser l'étudiant existant
            A.setNom(S.getNom());
            A.setPrenom(S.getPrenom());
            A.setCne(S.getCne());
            Repo.save(A);
            return "update successfully";
        }
        return "student not found";
    }


    @Override
    public String delete(long id) {
        if(Repo.existsById(id)){
            Repo.deleteById(id);
            return "delete successfully";
        }
       return "student not found";
    }

    @Override
    public List<Student> list() {
        return Repo.findAll();
    }

    @Override
    public Optional<Student> studentById(long id) {
        return Repo.findById(id);
    }
}
