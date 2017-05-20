package rgeoroceanu.model.access;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rgeoroceanu.model.Car;

@Repository
public interface CarDao extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
	
	@Query("SELECT c.make, COUNT(*) FROM Car c GROUP BY c.make")
	public List<Object[]> findMakesCount();
}
