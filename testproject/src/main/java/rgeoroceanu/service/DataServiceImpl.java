package rgeoroceanu.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

import rgeoroceanu.model.access.CarDao;
import rgeoroceanu.model.access.CarSearchSpecification;
import rgeoroceanu.model.access.DealershipDao;
import rgeoroceanu.model.access.PurchaseDao;
import rgeoroceanu.model.access.UserDao;
import rgeoroceanu.model.business.Car;
import rgeoroceanu.model.business.Dealership;
import rgeoroceanu.model.business.Price;
import rgeoroceanu.model.business.Purchase;
import rgeoroceanu.model.business.User;
import rgeoroceanu.model.cms.CarSearchCriteria;
import rgeoroceanu.model.type.CarType;
import rgeoroceanu.model.type.Currency;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Make;
import rgeoroceanu.model.type.Role;
import rgeoroceanu.model.type.State;
import rgeoroceanu.model.type.Transmission;
import rgeoroceanu.service.exception.DataDoesNotExistException;

@Service
public class DataServiceImpl implements DataService {
	
	private static final Logger LOG = Logger.getLogger(DataServiceImpl.class);
	@Autowired
	private CarDao carDao;
	@Autowired
	private PurchaseDao purchaseDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DealershipDao dealershipDao;
	
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
		final LocalDateTime currentDate = LocalDateTime.now();
		for (int i = 0; i < 12; i++) {
			final Purchase p = new Purchase();
			//p.setCar(car1);
			p.setCreated(currentDate.minusMonths(i));
			p.setSalePriceInEuro((i + 1) * 2 * 10000);
			this.savePurchase(p);
		}
		final Dealership dealership = new Dealership();
		dealership.setAddress("Peter-Kreuder Str.17");
		dealership.setCity("Munich");
		dealership.setCountry("Germany");
		dealership.setName("Radu Dealership");
		dealership.setPhone("+49 17658830284");
		dealership.setEmail("test@test.com");
		dealership.setZip("81245");
		this.saveDealership(dealership);
		final User user = new User();
		user.setRoles(new HashSet<>(Arrays.asList(Role.ADMIN)));
		user.setUsername("rgeoroceanu");
		user.setPassword("admin");
		this.saveUser(user);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Car getCar(Long id) throws DataDoesNotExistException {
		Preconditions.checkNotNull(id, "Id must not be null!");
		LOG.info("Requested car with id " + id);
		final Car car = carDao.findOne(id);
		if (car == null) {
			throw new DataDoesNotExistException("Entry not found!");
		}
		return car;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Car> getAllCars() {
		LOG.info("Requested all cars"); 	
		return carDao.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Car> getAllCarsBySearchCriteria(CarSearchCriteria searchCriteria) {
		Preconditions.checkNotNull(searchCriteria, "Search criteria must not be null!");
		LOG.info("Requested all cars by search criteria " + searchCriteria.toString()); 
		
		return carDao.findAll(new CarSearchSpecification(searchCriteria));
	}
	
	@Transactional
	@Override
	public Car saveCar(Car car) {
		Preconditions.checkNotNull(car, "Car must not be null!");
		LOG.info("Saving car " + car.toString()); 
		return carDao.saveAndFlush(car);
	}

	@Transactional
	@Override
	public void removeCar(Long id) throws DataDoesNotExistException {
		Preconditions.checkNotNull(id, "Id must not be null!");
		LOG.info("Remove car with id" + id); 
		if (carDao.findOne(id) == null) {
			throw new DataDoesNotExistException("Entry not found!");
		}
		carDao.delete(id);
	}
	
	@Transactional
	@Override
	public List<Car> getLatestCars(int resultsLimit) {
		LOG.info("Retrieve latest max " + resultsLimit + " cars");
		
		final Pageable pageRequest = new PageRequest(0, resultsLimit);
		final Page<Car> page = carDao.findAll(pageRequest);
		
		return page.getContent();
	}

	@Transactional
	@Override
	public Map<Make, Integer> getCarMakesCount() {
		LOG.info("Retrieve cars availability map"); 
		
		final Map<Make, Integer> makesCount = new HashMap<>();
		final List<Object[]> results = carDao.findMakesCount();
		
		for (Object[] result : results) {
			final Make make = (Make) result[0];
			final Long count = (Long) result[1];
			makesCount.put(make, count.intValue());
		}
		
		return makesCount;
	}
	
	@Transactional
	@Override
	public Purchase savePurchase(final Purchase purchase) {
		Preconditions.checkNotNull(purchase, "Purchase must not be null!");
		LOG.info("Save purchase " + purchase.toString()); 
		return purchaseDao.saveAndFlush(purchase);
	}
	
	@Transactional
	@Override
	public List<Purchase> getAllPurchases(final LocalDateTime start, final LocalDateTime end) {
		Preconditions.checkNotNull(start, "Start date must not be null!");
		Preconditions.checkNotNull(end, "end date must not be null!");
		LOG.info("Retrieve all purchases between " + start.toString() + " and " + end.toString()); 
		
		return purchaseDao.findInDateRange(start, end);
	}
	
	@Transactional
	@Override
	public Map<Integer, Integer> getMonthlyPurchasesCount(LocalDateTime start, LocalDateTime end) {
		Preconditions.checkNotNull(start, "Start date must not be null!");
		Preconditions.checkNotNull(end, "end date must not be null!");
		LOG.info("Retrieve monthly purchases count between " + start.toString() + " and " + end.toString()); 
		
		final Map<Integer, Integer> purchasesCount = new LinkedHashMap<>();
		final List<Object[]> results = purchaseDao.findPurchasesPerMonthCount(start, end);
		Collections.reverse(results);
		
		for (Object[] result : results) {
			final Integer month = (Integer) result[0];
			final Long count = (Long) result[1];
			purchasesCount.put(month, count.intValue());
		}
		
		return purchasesCount;
	}
	
	@Transactional
	@Override
	public Map<Integer, Integer> getMonthlyEarnings(LocalDateTime start, LocalDateTime end) {
		Preconditions.checkNotNull(start, "Start date must not be null!");
		Preconditions.checkNotNull(end, "end date must not be null!");
		LOG.info("Retrieve monthly earnings count between " + start.toString() + " and " + end.toString()); 
		
		final Map<Integer, Integer> earningsMap = new LinkedHashMap<>();
		final List<Object[]> results = purchaseDao.findEarningsPerMonth(start, end);
		Collections.reverse(results);
		
		for (Object[] result : results) {
			final Integer month = (Integer) result[0];
			final Long earnings = (Long) result[1];
			earningsMap.put(month, earnings.intValue());
		}
		
		return earningsMap;
	}
	
	@Transactional
	@Override
	public User getUser(Long id) throws DataDoesNotExistException {
		Preconditions.checkNotNull(id, "Id must not be null!");
		LOG.info("Requested user with id " + id);
		
		final User user = userDao.findOne(id);
		
		if (user == null) {
			throw new DataDoesNotExistException("Entry not found!");
		}
		
		return user;
	}
	
	@Transactional
	@Override
	public User getUser(String username) throws DataDoesNotExistException {
		Preconditions.checkNotNull(username, "Id must not be null!");
		LOG.info("Requested user with username " + username);
		
		final User user = userDao.findByUsername(username);
		
		if (user == null) {
			throw new DataDoesNotExistException("Entry not found!");
		}
		
		return user;
	}
	
	@Transactional
	@Override
	public List<User> getAllUsers() {
		LOG.info("Requested all cars"); 	
		return userDao.findAll();
	}
	
	@Transactional
	@Override
	public User saveUser(User user) {
		Preconditions.checkNotNull(user, "User must not be null!");
		LOG.info("Save user " + user.toString()); 
		return userDao.saveAndFlush(user);
	}

	@Transactional
	@Override
	public void removeUser(Long id) throws DataDoesNotExistException {
		Preconditions.checkNotNull(id, "Id must not be null!");
		LOG.info("Remove user with id" + id); 
		if (userDao.findOne(id) == null) {
			throw new DataDoesNotExistException("Entry not found!");
		}
		userDao.delete(id);
	}
	
	@Override
	public Dealership getDealership() throws DataDoesNotExistException {
		LOG.info("Requested dealership information");		
		final List<Dealership> dealerships = dealershipDao.findAll();
		
		if (dealerships.isEmpty()) {
			throw new DataDoesNotExistException("Entry not found!");
		}
		
		return dealerships.get(0);
	}

	@Override
	public Dealership saveDealership(Dealership dealership) {
		Preconditions.checkNotNull(dealership, "Dealership must not be null!");
		LOG.info("Save dealership " + dealership.toString());
		return dealershipDao.saveAndFlush(dealership);
	}
}
