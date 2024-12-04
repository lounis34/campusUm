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
    @Column(name = "campus", insertable = false, updatable = false)
    private String campus;

    @ManyToOne
    @JoinColumn(name = "campus")
    private Campus campusC;

    @OneToMany(mappedBy = "batiment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Salle> salles;

    @ManyToMany
    @JoinTable(name = "exploite",
            joinColumns = @JoinColumn(name = "building"),
            inverseJoinColumns = @JoinColumn(name = "team"))
    private List<Composante> composantes = new ArrayList<>();

    // Getters and Setters
    public List<Salle> getSalles() {
        return salles;
    }

    public void setSalles(List<Salle> salles) {
        this.salles = salles;
    }

    public int getAnneeC() {
        return anneeC;
    }

    public String getCodeB() {
        return codeB;
    }

    public List<Composante> getComposantes() {
        return composantes;
    }

    public void setComposantes(List<Composante> composantes) {
        this.composantes = composantes;
    }
}