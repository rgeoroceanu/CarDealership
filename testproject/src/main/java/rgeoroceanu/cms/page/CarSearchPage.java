package rgeoroceanu.cms.page;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

import rgeoroceanu.cms.layout.CarOverview;
import rgeoroceanu.model.Car;

@Component
public class CarSearchPage extends Page {
	
	private static final long serialVersionUID = 1L;
	private final VerticalLayout itemsLayout;
	
	public CarSearchPage() {
		super();
		itemsLayout = new VerticalLayout();
		this.setContent(itemsLayout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) { 
		final List<Car> cars = dataService.getAllCars();
		setResultsList(cars);
	}
	
	private void setResultsList(final List<Car> resultsList) {
		itemsLayout.removeAllComponents();
		for (Car car : resultsList) {
			final CarOverview carOverview = buildCarOverview(car);
			itemsLayout.addComponent(carOverview);
		}
	}
	
	private CarOverview buildCarOverview(final Car car) {
		final CarOverview carOverview = new CarOverview();
		carOverview.setTitle(car.getMarque() + " " + car.getModel());
		carOverview.setSubtitle(car.getShortDescription());
		carOverview.setDetails(car.getState().toString(), car.getRegistrationMonth() 
				+ "/" + car.getRegistrationYear(), car.getEngine().toString(), String.valueOf(car.getCubicCentimeters()), 
				car.getTransmission().toString(), String.valueOf(car.getHorsePower()), String.valueOf(car.getDoors()));
		carOverview.setPrice("49000 €", "55000 €");
		carOverview.localize();
		return carOverview;
	}
}
