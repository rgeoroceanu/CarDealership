package rgeoroceanu;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rgeoroceanu.model.business.Car;
import rgeoroceanu.model.business.Dealership;
import rgeoroceanu.model.business.Price;
import rgeoroceanu.model.business.Purchase;
import rgeoroceanu.model.business.User;
import rgeoroceanu.model.type.CarType;
import rgeoroceanu.model.type.Currency;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Make;
import rgeoroceanu.model.type.Role;
import rgeoroceanu.model.type.State;
import rgeoroceanu.model.type.Transmission;
import rgeoroceanu.service.DataService;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseTest {
	
	@Autowired
	private DataService dataService;
	
	@Before
	public void init() {
		addUsers();
		addCars();
		addDealership();
		addPurchases();
	}
	
	private void addCars() {
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
		dataService.saveCar(car1);
		dataService.saveCar(car2);
	}
	
	private void addUsers() {
		final User user = new User();
		user.setRoles(new HashSet<>(Arrays.asList(Role.ADMIN)));
		user.setUsername("rgeoroceanu");
		user.setPassword("admin");
		dataService.saveUser(user);
	}
	
	private void addDealership() {
		final Dealership dealership = new Dealership();
		dealership.setAddress("Peter-Kreuder Str.17");
		dealership.setCity("Munich");
		dealership.setCountry("Germany");
		dealership.setName("Radu Dealership");
		dealership.setPhone("+49 17658830284");
		dealership.setEmail("test@test.com");
		dealership.setZip("81245");
		dataService.saveDealership(dealership);
	}
	
	private void addPurchases() {
		final LocalDateTime currentDate = LocalDateTime.now();
		for (int i = 0; i < 12; i++) {
			final Purchase p = new Purchase();
			//p.setCar(car1);
			p.setCreated(currentDate.minusMonths(i));
			p.setSalePriceInEuro((i + 1) * 2 * 10000);
			dataService.savePurchase(p);
		}
	}
}
