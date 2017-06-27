package rgeoroceanu.cardealership.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import rgeoroceanu.cardealership.BaseTest;
import rgeoroceanu.cardealership.model.business.Car;
import rgeoroceanu.cardealership.model.business.Dealership;
import rgeoroceanu.cardealership.model.business.Purchase;
import rgeoroceanu.cardealership.model.business.User;
import rgeoroceanu.cardealership.model.cms.CarSearchCriteria;
import rgeoroceanu.cardealership.model.type.CarType;
import rgeoroceanu.cardealership.model.type.Engine;
import rgeoroceanu.cardealership.model.type.Make;
import rgeoroceanu.cardealership.model.type.Role;
import rgeoroceanu.cardealership.model.type.State;
import rgeoroceanu.cardealership.model.type.Transmission;
import rgeoroceanu.cardealership.service.DataService;
import rgeoroceanu.cardealership.service.exception.DataDoesNotExistException;

/**
 * Test data service.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class DataServiceTest extends BaseTest {
	
	@Autowired
	private DataService dataService;
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testGetCar() throws DataDoesNotExistException {
		final Car car = dataService.getCar(1l);
		assertThat(car.getId()).isEqualTo(1l);
		assertThat(car.getCarType()).isEqualTo(CarType.SALOON);
		assertThat(car.getModel()).isEqualTo("330i");
		assertThat(car.getCubicCentimeters()).isEqualTo(2000);
		assertThat(car.getMake()).isEqualTo(Make.BMW);
		assertThat(car.getShortDescription()).isEqualTo("Test desc 1");
		assertThat(car.getDoors()).isEqualTo(2);
		assertThat(car.getEngine()).isEqualTo(Engine.PETROL);
		assertThat(car.getRegistrationDate()).isEqualTo(LocalDate.of(2013, 11, 1));
		assertThat(car.getState()).isEqualTo(State.OLD);
		assertThat(car.getHorsePower()).isEqualTo(100);
		assertThat(car.getTransmission()).isEqualTo(Transmission.MANUAL);
	}
	
	@Test
	public void testCarNotFound() throws DataDoesNotExistException {
		exception.expect(DataDoesNotExistException.class);
		dataService.getCar(123l);
	}
	
	@Test
	public void testSearchCar() {
		final CarSearchCriteria criteria = new CarSearchCriteria();
		criteria.setMake(Make.LANDROVER);
		criteria.setStartYear(LocalDate.of(2014, 1, 1));
		criteria.setEndYear(LocalDate.of(2017, 1, 1));
		final List<Car> results = dataService.getAllCarsBySearchCriteria(criteria);
		assertThat(results).isNotEmpty();
		assertThat(results).hasSize(1);
		assertThat(results.get(0).getMake()).isEqualTo(Make.LANDROVER);
	}
	
	@Test
	public void testSearchNoResult() throws DataDoesNotExistException {
		final CarSearchCriteria criteria = new CarSearchCriteria();
		criteria.setMake(Make.MERCEDES);
		final List<Car> results = dataService.getAllCarsBySearchCriteria(criteria);
		assertThat(results).isEmpty();
	}
	
	@Test
	public void testSearchCarNoResult() {
		final CarSearchCriteria criteria = new CarSearchCriteria();
		criteria.setMake(Make.AUDI);
		List<Car> results = dataService.getAllCarsBySearchCriteria(criteria);
		assertThat(results).isEmpty();
	}
	
	@Test
	public void testGetAllCars() {
		final List<Car> results = dataService.getAllCars();
		assertThat(results).isNotEmpty();
		assertThat(results).hasSize(2);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveCar() {
		final Car car = new Car();
		car.setCarType(CarType.CABRIO);
		car.setMake(Make.CHEVROLET);
		car.setModel("Camaro SS");
		final Car saved = dataService.saveCar(car);
		assertThat(saved.getCarType()).isEqualTo(CarType.CABRIO);
		assertThat(saved.getModel()).isEqualTo("Camaro SS");
		assertThat(saved.getMake()).isEqualTo(Make.CHEVROLET);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemoveCar() throws DataDoesNotExistException {
		dataService.removeCar(1l);
		final List<Car> results = dataService.getAllCars();
		assertThat(results).hasSize(1);
		assertThat(results.get(0).getId()).isEqualTo(2l);
	}
	
	@Test
	public void testGetDealership() throws DataDoesNotExistException {
		final Dealership dealership = dataService.getDealership();
		assertThat(dealership.getName()).isEqualTo("Test Dealership");
		assertThat(dealership.getAddress()).isEqualTo("Peter-Kreuder Str.17");
		assertThat(dealership.getCity()).isEqualTo("Munich");
		assertThat(dealership.getCountry()).isEqualTo("Germany");
		assertThat(dealership.getPhone()).isEqualTo("+49 123456789");
		assertThat(dealership.getEmail()).isEqualTo("test@test.com");
		assertThat(dealership.getZip()).isEqualTo("81245");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveDealership() throws DataDoesNotExistException {
		final Dealership dealership = new Dealership();
		dealership.setName("Name");
		dataService.saveDealership(dealership);
		final Dealership saved = dataService.getDealership();
		assertThat(saved.getName()).isEqualTo("Name");
	}
	
	@Test
	public void testGetUser() throws DataDoesNotExistException {
		final User user = dataService.getUser(2l);	
		assertThat(user.getUsername()).isEqualTo("testuser");
		assertThat(user.getPassword().length()).isGreaterThan(20);
		assertThat(user.getRoles()).contains(Role.API);
	}
	
	@Test
	public void testUserNotFound() throws DataDoesNotExistException {
		exception.expect(DataDoesNotExistException.class);
		dataService.getUser(123l);
	}
	
	@Test
	public void testGetAllUsers() {
		final List<User> users = dataService.getAllUsers();
		assertThat(users).hasSize(2);
		assertThat(users.get(1).getUsername()).isEqualTo("testuser");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveUser() {
		final User user = new User();
		user.setRoles(new HashSet<>(Arrays.asList(Role.API)));
		user.setUsername("newuser");
		user.setPassword("password");
		final User saved = dataService.saveUser(user, true);
		assertThat(saved.getUsername()).isEqualTo("newuser");
		assertThat(saved.getPassword().length()).isGreaterThan(20);
		assertThat(saved.getRoles()).contains(Role.API);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testRemoveUser() throws DataDoesNotExistException {
		dataService.removeUser(1l);
		final List<User> results = dataService.getAllUsers();
		assertThat(results).hasSize(1);
	}
	
	@Test
	public void testGetAllPurchases() {
		final LocalDateTime currentDate = LocalDateTime.now();
		final List<Purchase> results = dataService.getAllPurchases(currentDate.minusYears(1), currentDate);
		assertThat(results).hasSize(12);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSavePurchase() throws DataDoesNotExistException {
		final Purchase purchase = new Purchase();
		purchase.setCar(dataService.getCar(1l));
		purchase.setSalePriceInEuro(1000);
		final Purchase saved = dataService.savePurchase(purchase);
		assertThat(saved.getSalePriceInEuro()).isEqualTo(1000);
		assertThat(saved.getCar()).isNotNull();
	}
	
	@Test
	public void testGetMonthlyPurchases() {
		final LocalDateTime currentDate = LocalDateTime.now();
		final Map<Integer, Integer> results = dataService.getMonthlyPurchasesCount(currentDate.minusYears(1), currentDate);
		assertThat(results).hasSize(12);
		assertThat(results.get(currentDate.getMonthValue())).isEqualTo(1);
	}
	
	@Test
	public void testGetMonthlyEarnings() {
		final LocalDateTime currentDate = LocalDateTime.now();
		final Map<Integer, Integer> results = dataService.getMonthlyEarnings(currentDate.minusYears(1), currentDate);
		assertThat(results).hasSize(12);
		assertThat(results.get(currentDate.getMonthValue())).isEqualTo(20000);
	}
}
