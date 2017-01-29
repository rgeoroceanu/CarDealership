package rgeoroceanu.model.access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rgeoroceanu.model.persistence.VehiclePers;

@Repository
public interface VehicleDao extends JpaRepository<VehiclePers, Long> {
	
}
