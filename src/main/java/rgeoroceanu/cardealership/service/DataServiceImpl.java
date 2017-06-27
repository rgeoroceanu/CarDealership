package rgeoroceanu.cardealership.service;

import java.time.LocalDateTime;
import java.util.Arrays;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

import rgeoroceanu.cardealership.model.access.CarDao;
import rgeoroceanu.cardealership.model.access.CarSearchSpecification;
import rgeoroceanu.cardealership.model.access.DealershipDao;
import rgeoroceanu.cardealership.model.access.PurchaseDao;
import rgeoroceanu.cardealership.model.access.UserDao;
import rgeoroceanu.cardealership.model.business.Car;
import rgeoroceanu.cardealership.model.business.Dealership;
import rgeoroceanu.cardealership.model.business.Purchase;
import rgeoroceanu.cardealership.model.business.User;
import rgeoroceanu.cardealership.model.cms.CarSearchCriteria;
import rgeoroceanu.cardealership.model.type.Make;
import rgeoroceanu.cardealership.model.type.Role;
import rgeoroceanu.cardealership.service.exception.DataDoesNotExistException;

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
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		// add initial user
		if (userDao.findAll().isEmpty()) {
			final User tempUser = new User();
			tempUser.setUsername("tempuser");
			tempUser.setPassword("temppass");
			tempUser.setRoles(new HashSet<>(Arrays.asList(Role.values())));
			userDao.saveAndFlush(tempUser);
		}
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
	public User saveUser(User user, boolean encodePassword) {
		Preconditions.checkNotNull(user, "User must not be null!");
		LOG.info("Save user " + user.getUsername());
		
		if (encodePassword) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
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
	@Transactional
	public Dealership getDealership() throws DataDoesNotExistException {
		LOG.info("Requested dealership information");		
		final List<Dealership> dealerships = dealershipDao.findAll();
		
		if (dealerships.isEmpty()) {
			throw new DataDoesNotExistException("Entry not found!");
		}
		
		return dealerships.get(0);
	}

	@Override
	@Transactional
	public Dealership saveDealership(Dealership dealership) {
		Preconditions.checkNotNull(dealership, "Dealership must not be null!");
		LOG.info("Save dealership " + dealership.toString());
		Dealership savedDealership;
		try {
			savedDealership = this.getDealership();
		} catch (DataDoesNotExistException e) {
			savedDealership = null;
		}
		if (savedDealership != null) {
			dealership.setId(savedDealership.getId());
		}
		return dealershipDao.saveAndFlush(dealership);
	}
}
