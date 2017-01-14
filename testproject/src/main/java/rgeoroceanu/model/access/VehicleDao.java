package rgeoroceanu.model.access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rgeoroceanu.model.business.Vehicle;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Long>{
	
}
