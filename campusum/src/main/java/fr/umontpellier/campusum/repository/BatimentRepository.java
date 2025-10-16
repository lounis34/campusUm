package fr.umontpellier.campusum.repository;

import fr.umontpellier.campusum.entity.Batiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatimentRepository extends JpaRepository<Batiment, String> {
    List<Batiment> findByCampusNomC(String nomC);
}




