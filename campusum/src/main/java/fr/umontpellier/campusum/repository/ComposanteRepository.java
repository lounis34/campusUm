package fr.umontpellier.campusum.repository;

import fr.umontpellier.campusum.entity.Composante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposanteRepository extends JpaRepository<Composante, String> {
    // String = type de la clé primaire (acronyme)
    // Tu peux ajouter des méthodes de requêtes dérivées si nécessaire
}
