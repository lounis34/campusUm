package fr.umontpellier.campusum.repository;

import fr.umontpellier.campusum.entity.Batiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BatimentRepository extends CrudRepository<Batiment, Long> {
    List<Batiment> findByCampusC_NomC(String campusNomC);
    }




