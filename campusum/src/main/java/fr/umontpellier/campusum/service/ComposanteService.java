package fr.umontpellier.campusum.service;

import fr.umontpellier.campusum.entity.Composante;
import fr.umontpellier.campusum.repository.ComposanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComposanteService {

    @Autowired
    private ComposanteRepository composanteRepository;

    // Liste de toutes les composantes
    public List<Composante> getAllComposantes() {
        return composanteRepository.findAll();
    }

    // Récupérer une composante par son acronyme (clé primaire)
    public Optional<Composante> getComposanteById(String acronyme) {
        return composanteRepository.findById(acronyme);
    }

    // Sauvegarder ou mettre à jour une composante
    public Composante saveComposante(Composante composante) {
        return composanteRepository.save(composante);
    }

    // Supprimer une composante par acronyme
    public void deleteComposante(String acronyme) {
        composanteRepository.deleteById(acronyme);
    }
}
