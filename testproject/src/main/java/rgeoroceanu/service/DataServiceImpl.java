package rgeoroceanu.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import rgeoroceanu.model.Car;
import rgeoroceanu.model.CarSearchCriteria;
import rgeoroceanu.model.Price;
import rgeoroceanu.model.access.CarDao;
import rgeoroceanu.model.access.CarSearchSpecification;
import rgeoroceanu.model.type.CarType;
import rgeoroceanu.model.type.Currency;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Make;
import rgeoroceanu.model.type.State;
import rgeoroceanu.model.type.Transmission;
import rgeoroceanu.service.exception.DataDoesNotExistException;

@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	private CarDao carDao;
	
	@PostConstruct
	public void init() {
		final Car car1 = new Car();
		car1.setCarType(CarType.SALOON);
		car1.setModel("330i");
		car1.setCubicCentimeters(2000);
		car1.setMake(Make.BMW);
		car1.setShortDescription("Test desc 1");
		car1.setDoors(2);
		car1.setEngine(Engine.PETROL);
		car1.setRegistrationMonth(11);
		car1.setRegistrationYear(2014);
		car1.setState(State.OLD);
		car1.setHorsePower(100);
		car1.setTransmission(Transmission.MANUAL);
		final Price p1 = new Price();
		p1.setCurrency(Currency.EURO);
		p1.setOriginalPrice(49000);
		p1.setDiscountedPrice(44900);
		car1.setPrice(p1);
		final Car car2 = new Car();
		car2.setCarType(CarType.SALOON);
		car2.setCubicCentimeters(3500);
		car2.setModel("Freelander");
		car2.setMake(Make.LANDROVER);
		car2.setShortDescription("Test desc 2");
		car2.setDoors(4);
		car2.setEngine(Engine.PETROL);
		car2.setRegistrationMonth(05);
		car2.setRegistrationYear(2014);
		car2.setState(State.OLD);
		car2.setHorsePower(100);
		car2.setTransmission(Transmission.MANUAL);
		final Price p2 = new Price();
		p2.setCurrency(Currency.EURO);
		p2.setOriginalPrice(49000);
		p2.setDiscountedPrice(44900);
		car2.setPrice(p2);
		this.saveCar(car1);
		this.saveCar(car2);
	}
	
	@Override
	public Car getCar(Long id) throws DataDoesNotExistException {
		Preconditions.checkNotNull(id, "Id must not be null!");
		return carDao.findOne(id);
	}

	@Override
	public List<Car> getAllCars() {
		return carDao.findAll();
	}
	
	@Override
	public List<Car> getAllCarsBySearchCriteria(CarSearchCriteria searchCriteria) {
		Preconditions.checkNotNull(searchCriteria, "Search criteria must not be null!");
		return carDao.findAll(new CarSearchSpecification(searchCriteria));
	}
	
	@Override
	public Car saveCar(Car car) {
		Preconditions.checkNotNull(car, "Car must not be null!");
		return carDao.saveAndFlush(car);
	}

	@Override
	public void removeCar(Long id) throws DataDoesNotExistException {
		Preconditions.checkNotNull(id, "Id must not be null!");
		carDao.delete(id);
	}
}
