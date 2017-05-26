package rgeoroceanu.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import rgeoroceanu.model.business.Car;
import rgeoroceanu.model.business.Purchase;
import rgeoroceanu.model.cms.CarSearchCriteria;
import rgeoroceanu.model.type.Make;
import rgeoroceanu.service.exception.DataDoesNotExistException;

/**
 * Service that is used as a bridge between the DAO layer and the business logic of the application.
 * Can have different implementations for different purposes like mocking, caching, transactions etc.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public interface DataService {
	
	/**
	 * Retrieve {@link Car} by id.
	 * @param id of the car to retrieve.
	 * @return car entity corresponding to the id.
	 * @throws DataDoesNotExistException in case the entry does not exist.
	 */
	public Car getCar(final Long id) throws DataDoesNotExistException;
	
	/**
	 * Retrieve all cars.
	 * @return list of all available cars.
	 */
	public List<Car> getAllCars();
	
	/**
	 * Create or update the {@link Car} entity. If the id is null, a new entity is saved.
	 * @param car to be saved.
	 * @return the saved car entity.
	 */
	public Car saveCar(final Car car);
	
	/**
	 * Remove the car entity associated with the provided id.
	 * @param carId of the car to delete.
	 * @throws DataDoesNotExistException thrown in case no car with the provided id exists.
	 */
	public void removeCar(final Long carId) throws DataDoesNotExistException;
	
	/**
	 * Retrieve a car by the provided {@link CarSearchCriteria}.
	 * @param searchCriteria that contains search terms.
	 * @return all matching car entities.
	 */
	public List<Car> getAllCarsBySearchCriteria(CarSearchCriteria searchCriteria);
	
	/**
	 * Retrieve a certain number of the latest added {@link Car} entities.
	 * @param resultsLimit maximum size of the list to retrieve.
	 * @return latest cars.
	 */
	public List<Car> getLatestCars(final int resultsLimit);
	
	/**
	 * Retrieve current car makes mapped by their count.
	 * @return per make car count.
	 */
	public Map<Make, Integer> getCarMakesCount();
	
	/**
	 * Create or update the provided {@link Purchase}. If the id is null, a new entity is created.
	 * @param purchase to be saved.
	 * @return the saved purchase.
	 */
	public Purchase savePurchase(final Purchase purchase);
	
	/**
	 * Retrieve a list of all purchases within a time period.
	 * @param start date
	 * @param end date
	 * @return purchases between start date and end date.
	 */
	public List<Purchase> getAllPurchases(final LocalDateTime start, final LocalDateTime end);
	
	/**
	 * Retrieve per month purchases count, between a certain time period.
	 * @param start date
	 * @param end date
	 * @return per month purchases count.
	 */
	public Map<Integer, Integer> getMonthlyPurchasesCount(final LocalDateTime start, final LocalDateTime end);
	
	/**
	 * Retrieve per month earnings, between a certain time period.
	 * @param start date
	 * @param end date
	 * @return per month earnings.
	 */
	public Map<Integer, Integer> getMonthlyEarnings(final LocalDateTime start, final LocalDateTime end);
}
