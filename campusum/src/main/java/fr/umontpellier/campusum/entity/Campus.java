package fr.umontpellier.campusum.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Campus")
public class Campus {



        @Id
        @Column(name = "nomC")
        private String nomC;
        @Column(name = "ville")
        private String ville;


/*
    @OneToMany(mappedBy = "campus")
    private List<Batiment> batiments;*/
    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Batiment> batiments = new ArrayList<>();

    // Getters et Setters
    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public List<Batiment> getBatiments() {
        return batiments;
    }

    public void setBatiments(List<Batiment> batiments) {
        this.batiments = batiments;
        for (Batiment batiment : batiments) {
            batiment.setCampus(this);
        }
    }
}
