package fr.umontpellier.campusum.repository;

import fr.umontpellier.campusum.entity.Campus;
import org.springframework.data.repository.CrudRepository;

public interface CampusRepository extends CrudRepository <Campus, String> {

}
