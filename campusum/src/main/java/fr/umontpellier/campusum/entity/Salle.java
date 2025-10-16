package fr.umontpellier.campusum.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "salle")

public class Salle {


    @Id
    @Column(name = "numS", unique = true, nullable = false)
    private String numS;

    @Column(name = "capacite", nullable = false)
    private int capacite;

    @Column(name = "typeS")
    private String typeS;

    @Column(name = "acces")
    private String acces;

    @Column(name = "etage")
    private String etage;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "batiment", referencedColumnName = "codeB", nullable = false)
    private Batiment batiment;
/*
    @ManyToMany
    @JoinTable(
            name = "exploite_salle_composante",
            joinColumns = @JoinColumn(name = "salle_numS", referencedColumnName = "numS"),
            inverseJoinColumns = @JoinColumn(name = "composante_acronyme", referencedColumnName = "acronyme")
    )
    private List<Composante> composantes = new ArrayList<>();

*/

    // Getters et Setters
    public String getNumS() {
        return numS;
    }

    public void setNumS(String numS) {
        this.numS = numS;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getTypeS() {
        return typeS;
    }

    public void setTypeS(String typeS) {
        this.typeS = typeS;
    }

    public String getAcces() {
        return acces;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;

        if (batiment != null) {
            if (!batiment.getSalles().contains(this)) {
                batiment.getSalles().add(this);
            }
        }
    }


}
