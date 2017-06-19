package rgeoroceanu.cardealership.model.access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import rgeoroceanu.cardealership.model.business.Dealership;

/**
 * DAO that provides methods for handling {@link Dealership} entities.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Repository
@RepositoryRestResource
public interface DealershipDao extends JpaRepository<Dealership, Long> {
	
	@Override
	@RestResource(exported = false)
	public <S extends Dealership> S save(S entity);
	
	@Override
	@RestResource(exported = false)
	public <S extends Dealership> S saveAndFlush(S entity);
	
	@Override
	@RestResource(exported = false)
	public void delete(Long id);

	@Override
	@RestResource(exported = false)
	public void delete(Dealership entity);
}
