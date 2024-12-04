package fr.umontpellier.campusum.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Campus")
public class Campus {



        @Id
        @Column(name = "nomC")
        private String nomC;
        @Column(name = "ville")
        private String ville;



    @OneToMany(mappedBy = "campus")
    private List<Batiment> batiments;

    // Getters and Setters


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

   /* public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
*/
    public List<Batiment> getBatiments() {
        return batiments;
    }

    public void setBatiments(List<Batiment> batiments) {
        this.batiments = batiments;}


}
