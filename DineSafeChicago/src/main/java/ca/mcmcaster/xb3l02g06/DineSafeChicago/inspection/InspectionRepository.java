package ca.mcmcaster.xb3l02g06.DineSafeChicago.inspection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionRepository extends CrudRepository<Inspection, Long>{

}
