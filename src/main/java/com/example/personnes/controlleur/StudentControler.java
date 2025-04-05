package com.example.personnes.controlleur;

import com.example.personnes.model.Student;
import com.example.personnes.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentControler {
    final private StudentService servic;


    public StudentControler(StudentService servic) {
        this.servic = servic;
    }
    @PostMapping("/admin/create")
    public String create(@RequestBody Student S){
       return servic.CreatStudent(S);
    }
    @PutMapping("/admin/update")
    public String update(@RequestBody Student S){
        return servic.UpdateStudent(S);
    }
    @DeleteMapping("/admin/delete{id}")
    public String delete(@PathVariable long id){
        return servic.delete(id);
    }
    @GetMapping("/user/list")
    List<Student> all(){
        return servic.list();
    }
    @GetMapping("/student/{id}")
    public Optional<Student> StudentById(@RequestParam long id){
        return servic.studentById(id);
    }

}
