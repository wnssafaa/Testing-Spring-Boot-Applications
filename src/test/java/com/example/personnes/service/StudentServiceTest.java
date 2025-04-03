package com.example.personnes.service;

import com.example.personnes.model.Student;
import com.example.personnes.repo.StudentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock//pour ne pas interagir avec la vrai base
    private StudentRepo Repo;
    @InjectMocks
    private StudentService service;
    @Test
    public void testcreat(){
        Student s=new Student("safaa","wns","CNE123456");
        String result=service.CreatStudent(s);
        assertEquals("student add Successfully", result);//verification que il a ajouter avec sucsse
        verify(Repo, times(1)).save(s);
    }
    @Test
    public void updateStudent(){
        long id=999L;
        Student update=new Student("safaa","wns","CNE123456");
        update.setId(id);
        when(Repo.findById(id)).thenReturn(Optional.empty());
        String result = service.UpdateStudent(update);

        // Assert
        assertEquals("student not found", result);
        verify(Repo, never()).save(any());
    }
    @Test
    public void deletestudent(){
        long id=229l;
        when(Repo.existsById(id)).thenReturn(true);
        String result=service.delete(id);
        assertEquals("delete successfully",result);
        verify(Repo,times(1)).deleteById(id);
    }

    @Test
    public void findall(){
        when(Repo.findAll()).thenReturn(List.of());
        List<Student> result=service.list();
        assertTrue(result.isEmpty(),"la list devrait etre vide");
        verify(Repo).findAll();
    }
    @Test
    public void listnotemty(){
        Student student1 = new Student("safaa", "wns", "CNE123456");
        Student student2 = new Student("mohammed", "ahmed", "CNE654321");
        when(Repo.findAll()).thenReturn(List.of(student1, student2));
        // Appeler la méthode du service
        List<Student> result = service.list();

        // Vérifier que la liste n'est pas vide
        assertFalse(result.isEmpty(), "La liste des étudiants ne doit pas être vide");
        assertEquals(2, result.size(), "La taille de la liste devrait être de 2");
        verify(Repo, times(1)).findAll(); // Vérifier que findAll a été appelé une fois
    }
 /*   @Test
    void testUpdateStudent_Success() {
        Student Student = null;
        when(Repo.findById(1L)).thenReturn(Optional.of(Student));
        Student updatedStudent = new Student(1L, "UpdatedName", "UpdatedPrenom", "CNE123");

        String result = service.UpdateStudent(updatedStudent);

        assertEquals("update successfully", result);
        verify(Repo, times(1)).save(any(Student.class));
    }*/
}
