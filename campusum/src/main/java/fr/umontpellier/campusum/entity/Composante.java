package fr.umontpellier.campusum.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "composante")
public class Composante {
    @Id
    @Column(name = "acronyme")
    private String acronyme;
    @Column(name = "nom")
    private String nom;
    @Column(name = "responsable")
    private String responsable;


    @ManyToMany(mappedBy = "composantes")
    private List<Batiment> batiments = new ArrayList<>();


    // Getters and Setters
    public String getAcronyme() {
        return acronyme;
    }

    public void setAcronyme(String acronyme) {
        this.acronyme = acronyme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public List<Batiment> getBatiments() {
        return batiments;
    }

    public void setBatiments(List<Batiment> batiments) {
        this.batiments = batiments;
    }
}