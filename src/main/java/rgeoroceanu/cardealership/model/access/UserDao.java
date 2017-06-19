package rgeoroceanu.cardealership.model.access;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rgeoroceanu.cardealership.model.business.User;

/**
 * DAO that provides methods for handling {@link User} entities.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
}
