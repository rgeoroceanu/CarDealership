package rgeoroceanu.service;

import java.util.List;

import rgeoroceanu.model.Car;
import rgeoroceanu.service.exception.DataDoesNotExistException;

public interface DataService {
	public Car getCar(final Long id) throws DataDoesNotExistException;
	public List<Car> getAllCars();
	public Car saveCar(final Car car);
	public void removeCar(final Long carId) throws DataDoesNotExistException;
}
