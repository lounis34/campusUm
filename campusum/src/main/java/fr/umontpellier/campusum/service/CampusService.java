package fr.umontpellier.campusum.service;

import fr.umontpellier.campusum.entity.Campus;
import fr.umontpellier.campusum.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampusService {

    private final CampusRepository campusRepository;

    @Autowired
    public CampusService(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    public List<Campus> getAllCampus() {
        return campusRepository.findAll();
    }

    public Optional<Campus> getCampusByName(String nomC) {
        return campusRepository.findById(nomC);
    }


    public Campus saveCampus(Campus campus) {
        return campusRepository.save(campus);
    }

    public void deleteCampus(String nomC) {
        campusRepository.deleteById(nomC);
    }


}
