package rgeoroceanu.model.access;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rgeoroceanu.model.business.Purchase;

/**
 * DAO that provides methods for accessing {@link Purchase} entities.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Repository
public interface PurchaseDao extends JpaRepository<Purchase, Long> {
	
	@Query("SELECT MONTH(p.created), COUNT(*) "
			+ "FROM Purchase p WHERE p.created BETWEEN :start AND :end GROUP BY MONTH(p.created)")
	public List<Object[]> findPurchasesPerMonthCount(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
	
	@Query("SELECT MONTH(p.created), SUM(p.salePriceInEuro) "
			+ "FROM Purchase p WHERE p.created BETWEEN :start AND :end GROUP BY MONTH(p.created)")
	public List<Object[]> findEarningsPerMonth(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
	
	@Query("SELECT p FROM Purchase p WHERE p.created BETWEEN :start AND :end")
	public List<Purchase> findInDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
