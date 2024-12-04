package fr.umontpellier.campusum.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "salle")

public class Salle {
    @Id
    @Column(name = "numS")
    private String numS;
    @Column(name = "capacite")
    private Integer capacite;
    @Column(name = "typeS")
    private String typeS;
    @Column(name = "acces")
    private String acces;
    @Column(name = "etage")
    private String etage;




    @ManyToOne
    @JoinColumn(name = "batiment")
    @JsonBackReference
    private Batiment batiment;




//getters et setters

    public String getNumS() {
        return numS;
    }

    public void setNumS(String numS) {
        this.numS = numS;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
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
    }
}
