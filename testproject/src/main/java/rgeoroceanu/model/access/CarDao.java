package rgeoroceanu.model.access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import rgeoroceanu.model.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
	
}
