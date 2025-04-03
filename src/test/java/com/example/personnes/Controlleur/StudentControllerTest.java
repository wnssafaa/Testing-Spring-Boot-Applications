package com.example.personnes.Controlleur;

import com.example.personnes.controlleur.StudentControler;
import com.example.personnes.model.Student;
import com.example.personnes.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentControler.class)
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc; // Permet d’envoyer des requêtes HTTP simulées.

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentControler studentControler;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentControler).build();
    }

    @Test
    void testCreateStudent() throws Exception {
        when(studentService.CreatStudent(any(Student.class))).thenReturn("student add Successfully");

        String studentJson = "{\"nom\": \"Safaa\", \"prenom\": \"Elouannass\", \"cne\": \"CNE123\"}";

        mockMvc.perform(post("/api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk())
                .andExpect(content().string("student add Successfully"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        when(studentService.UpdateStudent(any(Student.class))).thenReturn("student updated Successfully");

        String studentJson = "{\"nom\": \"Safaa\", \"prenom\": \"Elouannass\", \"cne\": \"CNE123\"}";

        mockMvc.perform(put("/api/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJson))
                .andExpect(status().isOk())
                .andExpect(content().string("student updated Successfully"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        when(studentService.delete(1L)).thenReturn("student deleted Successfully");

        mockMvc.perform(delete("/api/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("student deleted Successfully"));
    }

    @Test
    void testGetAllStudents() throws Exception {
        Student student1 = new Student(1L, "Safaa", "Elouannass", "CNE123");
        Student student2 = new Student(2L, "Karima", "Ahmed", "CNE456");

        when(studentService.list()).thenReturn(Arrays.asList(student1, student2));

        mockMvc.perform(get("/api/list"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"nom\":\"Safaa\",\"prenom\":\"Elouannass\",\"cne\":\"CNE123\"}," +
                        "{\"id\":2,\"nom\":\"Karima\",\"prenom\":\"Ahmed\",\"cne\":\"CNE456\"}]"));
    }


    @Test
    void testGetStudentById() throws Exception {
        Student student = new Student(1L, "Safaa", "Elouannass", "CNE123");

        when(studentService.studentById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/api/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"nom\":\"Safaa\",\"prenom\":\"Elouannass\",\"cne\":\"CNE123\"}"));
    }
}
