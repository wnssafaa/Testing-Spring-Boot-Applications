package com.example.personnes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student2")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    @Column(nullable = false)
    private String prenom;

    @Column(unique = true)
    private String Cne;

    public Student() {

    }

    public Student(long l, String updatedName, String updatedPrenom, String cne123) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCne() {
        return Cne;
    }

    public void setCne(String cne) {
        Cne = cne;
    }

    public Student(String nom, String prenom, String cne) {
        this.nom = nom;
        this.prenom = prenom;
        Cne = cne;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", Cne='" + Cne + '\'' +
                '}';
    }
}
