package rgeoroceanu.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarSearchResult {
	
	private Long carId;
	private String title;
	private String subtitle;
	private String state;
	private String date;
	private String engine;
	private String capacity;
	private String transmission;
	private String power;
	private String price;
	private List<String> imageUrls = new ArrayList<>();
	
	public CarSearchResult(Car car) {
		final String title = car.getMake() + " " + car.getModel();
		final String subtitle = car.getShortDescription();
		final String state = car.getState() != null ? car.getState().toString() : "";
		final String date = car.getRegistrationMonth() + "/" + car.getRegistrationYear();
		final String engine = car.getEngine().toString();
		final String capacity = String.valueOf(car.getCubicCentimeters());
		final String transmission = car.getTransmission().toString();
		final String power = String.valueOf(car.getHorsePower());
		this.setCarId(car.getId());
		this.setTitle(title);
		this.setSubtitle(subtitle);
		this.setState(state); 
		this.setDate(date); 
		this.setEngine(engine);
		this.setCapacity(capacity);
		this.setTransmission(transmission);
		this.setPower(power);
		if (car.getPrice() != null) {
			final String displayPrice = car.getPrice().getDiscountedPrice() + " €";
			this.setPrice(displayPrice);
		}
	}
	
}
