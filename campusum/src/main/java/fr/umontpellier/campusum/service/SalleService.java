package fr.umontpellier.campusum.service;

import fr.umontpellier.campusum.entity.Salle;
import fr.umontpellier.campusum.repository.SalleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SalleService {

    @Autowired
    private SalleRepository salleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Salle> getSalleById(String numS) {
        return salleRepository.findById(numS);
    }

    public List<Salle> getSallesByBatiment(String codeB) {
        return salleRepository.findByBatimentCodeB(codeB);
    }

    @Transactional
    public void saveSalle(Salle salle) {
        if (salleRepository.existsById(salle.getNumS())) {
            throw new IllegalArgumentException("Erreur : La salle '" + salle.getNumS() + "' existe déjà !");
        }

        entityManager.detach(salle); // 💡 Détache l'entité avant l'insertion pour éviter les conflits
        entityManager.merge(salle); // 🔥 Utilise merge() au lieu de save() pour éviter le conflit de session

        salleRepository.save(salle);
    }

    @Transactional
    public void deleteSalle(String numS) {
        salleRepository.deleteById(numS);
    }

    @Transactional
    public void detachSalle(Salle salle) {
        if (entityManager.contains(salle)) {
            entityManager.detach(salle);
        }
    }

    @Transactional
    public void clearSession() {
        entityManager.clear();
    }

    @Transactional
    public Salle updateSalle(String numS, Salle updatedSalle) {
        Optional<Salle> existingSalle = salleRepository.findById(numS);
        if (existingSalle.isPresent()) {
            Salle salle = existingSalle.get();
            salle.setCapacite(updatedSalle.getCapacite());
            salle.setTypeS(updatedSalle.getTypeS());
            salle.setAcces(updatedSalle.getAcces());
            salle.setEtage(updatedSalle.getEtage());
            salle.setBatiment(updatedSalle.getBatiment());
            return entityManager.merge(salle);
        }
        return null;
    }
}
