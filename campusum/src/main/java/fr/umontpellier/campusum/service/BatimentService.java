package fr.umontpellier.campusum.service;

import fr.umontpellier.campusum.entity.Batiment;
import fr.umontpellier.campusum.repository.BatimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatimentService {

    private final BatimentRepository batimentRepository;

    public List<Batiment> getBatimentsByCampus(String nomC) {
        return batimentRepository.findByCampusNomC(nomC);
    }


    @Autowired
    public BatimentService(BatimentRepository batimentRepository) {
        this.batimentRepository = batimentRepository;
    }


    public List<Batiment> getAllBatiments() {
        return batimentRepository.findAll();
    }


    public Optional<Batiment> getBatimentById(String codeB) {
        return batimentRepository.findById(codeB);
    }


    public Batiment saveBatiment(Batiment batiment) {
        return batimentRepository.save(batiment);
    }

    /**
     * Supprime un Batiment via son codeB.
     */
    public void deleteBatiment(String codeB) {
        batimentRepository.deleteById(codeB);
    }
}
