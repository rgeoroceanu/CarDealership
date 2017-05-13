package rgeoroceanu.cms.component.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.vaadin.ui.VerticalLayout;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.model.Car;

public class CarSearchContainer extends VerticalLayout implements Localizable {
	
	private static final long serialVersionUID = 1L;
	private final Map<Car, CarOverview> resultsMap = new HashMap<>();
	
	public void addItems(final List<Car> items) {
		clear();
		for (final Car item : items) {
			final CarOverview overview = buildCarOverview(item);
			this.addComponent(overview);
			resultsMap.put(item, overview);
		}
		localize();
	}
	
	public void clear() {
		resultsMap.clear();
		this.removeAllComponents();
	}
	
	@Override
	public void localize() {
		resultsMap.values().forEach(overview -> overview.localize());	
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
		return carOverview;
	}

//	@Override
//	public void replaceComponent(Component oldComponent, Component newComponent) {
//		
//	}
//
//	@Override
//	public int getComponentCount() {
//		return resultsMap.size();
//	}
//
//	@Override
//	public Iterator<Component> iterator() {
//		Collection<Component> components = new ArrayList<Component>();
//		resultsMap.values().forEach(overview -> components.add(overview));
//		return components.iterator();
//	}
}
