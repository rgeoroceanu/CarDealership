package rgeoroceanu.cms.component.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.vaadin.ui.VerticalLayout;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.model.Car;

public class CarSearchContainer extends VerticalLayout implements Localizable {
	
	private static final long serialVersionUID = 1L;
	private final Map<CarOverview, Car> resultsMap = new HashMap<>();
	
	public void addItems(final List<Car> items) {
		clear();
		for (final Car item : items) {
			final CarOverview overview = buildCarOverview(item);
			overview.addEditListener(e -> App.getCurrent().navigateToCarPage(item.getId()));
			this.addComponent(overview);
			resultsMap.put(overview, item);
		}
		localize();
	}
	
	public void clear() {
		resultsMap.clear();
		this.removeAllComponents();
	}
	
	@Override
	public void localize() {
		resultsMap.keySet().forEach(overview -> overview.localize());	
	}
	
	private CarOverview buildCarOverview(final Car car) {
		Preconditions.checkNotNull(car);
		final CarOverview carOverview = new CarOverview();
		final String title = car.getMake() + " " + car.getModel();
		final String subtitle = car.getShortDescription();
		final String state = car.getState().toString();
		final String date = car.getRegistrationMonth() + "/" + car.getRegistrationYear();
		final String engine = car.getEngine().toString();
		final String capacity = String.valueOf(car.getCubicCentimeters());
		final String transmission = car.getTransmission().toString();
		final String power = String.valueOf(car.getHorsePower());
		carOverview.setTitle(title);
		carOverview.setSubtitle(subtitle);
		carOverview.setDetails(state, date, engine, capacity, transmission, power);
		if (car.getPrice() != null) {
			final String displayPrice = car.getPrice().getDiscountedPrice() + " €";
			carOverview.setPrice(displayPrice);
		}
		final String path = "http://localhost/images/" + car.getId();
		carOverview.setImageUrls(Arrays.asList(path + "/img1", path + "/img2"));
		return carOverview;
	}
}
