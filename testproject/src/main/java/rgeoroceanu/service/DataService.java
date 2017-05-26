package rgeoroceanu.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import rgeoroceanu.model.Car;
import rgeoroceanu.model.CarSearchCriteria;
import rgeoroceanu.model.Purchase;
import rgeoroceanu.model.type.Make;
import rgeoroceanu.service.exception.DataDoesNotExistException;

public interface DataService {
	public Car getCar(final Long id) throws DataDoesNotExistException;
	public List<Car> getAllCars();
	public Car saveCar(final Car car);
	public void removeCar(final Long carId) throws DataDoesNotExistException;
	public List<Car> getAllCarsBySearchCriteria(CarSearchCriteria searchCriteria);
	public List<Car> getLatestCars(final int resultsLimit);
	public Map<Make, Integer> getCarMakesCount();
	public Purchase savePurchase(final Purchase purchase);
	public List<Purchase> getAllPurchases(final LocalDateTime start, final LocalDateTime end);
	public Map<Integer, Integer> getMonthlyPurchasesCount(final LocalDateTime start, final LocalDateTime end);
	public Map<Integer, Integer> getMonthlyEarnings(final LocalDateTime start, final LocalDateTime end);
}
