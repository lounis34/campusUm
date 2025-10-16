package fr.umontpellier.campusum.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "batiment")


public class Batiment {


    @Id
    @Column(name = "codeB")
    private String codeB;

    @Column(name = "anneeC")
    private int anneeC;

   /* @Column(name = "campus", insertable = false, updatable = false)
    private String campus;

    @ManyToOne
    @JoinColumn(name = "campus")
    private Campus campusC;
*/
    @ManyToOne
    @JoinColumn(name = "campus", referencedColumnName = "nomC", nullable = false)
    private Campus campus;

    @OneToMany(mappedBy = "batiment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Salle> salles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "exploite",
            joinColumns = @JoinColumn(name = "building"),
            inverseJoinColumns = @JoinColumn(name = "team"))
    private List<Composante> composantes = new ArrayList<>();
/*
    @ManyToMany
    @JoinTable(
            name = "exploite",
            joinColumns = @JoinColumn(name = "codeB"),
            inverseJoinColumns = @JoinColumn(name = "acronyme")
    )
    private List<Composante> composantes = new ArrayList<>();
*/

    // Getters et Setters
    public String getCodeB() {
        return codeB;
    }

    public void setCodeB(String codeB) {
        this.codeB = codeB;
    }

    public int getAnneeC() {
        return anneeC;
    }

    public void setAnneeC(int anneeC) {
        this.anneeC = anneeC;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
        if (!campus.getBatiments().contains(this)) {
            campus.getBatiments().add(this);
        }
    }

    public List<Salle> getSalles() {
        return salles;
    }

    public void setSalles(List<Salle> salles) {
        this.salles = salles;
        for (Salle salle : salles) {
            salle.setBatiment(this);
        }
    }

    public List<Composante> getComposantes() {
        return composantes;
    }

    public void setComposantes(List<Composante> composantes) {
        this.composantes = composantes;
        for (Composante composante : composantes) {
            if (!composante.getBatiments().contains(this)) {
                composante.getBatiments().add(this);
            }
        }
    }
}


