package rgeoroceanu.cardealership.model.access;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import rgeoroceanu.cardealership.model.business.Car;
import rgeoroceanu.cardealership.model.cms.CarSearchCriteria;

/**
 * Specification for advanced search of {@link Car} entities.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class CarSearchSpecification implements Specification<Car> {

	private final CarSearchCriteria carSearchCriteria;
	
	public CarSearchSpecification(CarSearchCriteria carSearchCriteria) {
		this.carSearchCriteria = carSearchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Car> car, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (carSearchCriteria == null) {
			throw new IllegalStateException("Search criteria can not be null!");
		}
		List<Predicate> predicates = new ArrayList<Predicate>();
		if (carSearchCriteria.getMake() != null) {
			predicates.add(builder.and(builder.equal(car.get("make"), carSearchCriteria.getMake())));
		}
		if (carSearchCriteria.getModel() != null) {
			predicates.add(builder.and(builder.like(car.get("model"), "%" + carSearchCriteria.getModel().toLowerCase() + "%")));
		}
		if (carSearchCriteria.getEngine() != null) {
			predicates.add(builder.and(builder.equal(car.get("engine"), carSearchCriteria.getEngine())));
		}
		if (carSearchCriteria.getEngine() != null) {
			predicates.add(builder.and(builder.equal(car.get("engine"), carSearchCriteria.getEngine())));
		}
		if (carSearchCriteria.getStartYear() != null) {
			predicates.add(builder.and(builder.greaterThanOrEqualTo(car.get("registrationDate"), carSearchCriteria.getStartYear())));
		}
		if (carSearchCriteria.getEndYear() != null) {
			predicates.add(builder.and(builder.lessThanOrEqualTo(car.get("registrationDate"), carSearchCriteria.getEndYear())));
		}
		if (carSearchCriteria.getStartPower() != null) {
			predicates.add(builder.and(builder.greaterThanOrEqualTo(car.get("horsePower"), carSearchCriteria.getStartPower())));
		}
		if (carSearchCriteria.getEndPower() != null) {
			predicates.add(builder.and(builder.lessThanOrEqualTo(car.get("horsePower"), carSearchCriteria.getEndPower())));
		}
		if (carSearchCriteria.getStartCapacity() != null) {
			predicates.add(builder.and(builder.greaterThanOrEqualTo(car.get("cubicCentimeters"), carSearchCriteria.getStartCapacity())));
		}
		if (carSearchCriteria.getEndCapacity() != null) {
			predicates.add(builder.and(builder.lessThanOrEqualTo(car.get("cubicCentimeters"), carSearchCriteria.getEndCapacity())));
		}
		if (carSearchCriteria.getStartPrice() != null) {
			predicates.add(builder.and(builder.greaterThanOrEqualTo(car.get("discountedPrice"), carSearchCriteria.getStartPrice())));
		}
		if (carSearchCriteria.getEndPrice() != null) {
			predicates.add(builder.and(builder.lessThanOrEqualTo(car.get("discountedPrice"), carSearchCriteria.getEndPrice())));
		}
		Predicate[] predicatesArray = new Predicate[predicates.size()];
		return builder.and(predicates.toArray(predicatesArray));
	}
}
