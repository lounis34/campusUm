package fr.umontpellier.campusum.repository;

import fr.umontpellier.campusum.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalleRepository extends JpaRepository<Salle, String> {
    List<Salle> findByBatimentCodeB(String batimentCodeB);
    List<Salle> findByNumSIn(List<String> numS);

}