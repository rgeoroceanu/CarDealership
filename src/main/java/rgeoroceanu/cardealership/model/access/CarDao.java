package rgeoroceanu.cardealership.model.access;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import rgeoroceanu.cardealership.model.business.Car;

/**
 * DAO that provides methods for accessing {@link Car} entities.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Repository
@RepositoryRestResource
public interface CarDao extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
	
	@Query("SELECT c.make, COUNT(*) FROM Car c GROUP BY c.make")
	public List<Object[]> findMakesCount();
	
	@Override
	@RestResource(exported = false)
	public <S extends Car> S save(S entity);
	
	@Override
	@RestResource(exported = false)
	public <S extends Car> S saveAndFlush(S entity);
	
	@Override
	@RestResource(exported = false)
	public void delete(Long id);

	@Override
	@RestResource(exported = false)
	public void delete(Car entity);
}
