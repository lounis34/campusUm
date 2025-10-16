package fr.umontpellier.campusum.repository;

import fr.umontpellier.campusum.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusRepository extends JpaRepository<Campus, String> {
    Campus findCampusByNomC(String name);
}
