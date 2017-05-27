package rgeoroceanu.model.access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rgeoroceanu.model.business.Dealership;

/**
 * DAO that provides methods for handling {@link Dealership} entities.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Repository
public interface DealershipDao extends JpaRepository<Dealership, Long> {

}
