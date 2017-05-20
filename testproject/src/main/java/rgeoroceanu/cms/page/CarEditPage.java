package rgeoroceanu.cms.page;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import rgeoroceanu.cms.component.form.CarForm;
import rgeoroceanu.model.Car;
import rgeoroceanu.service.exception.DataDoesNotExistException;

@Component
public class CarEditPage extends Page {

	private static final long serialVersionUID = 1L;
	private final CarForm carForm;
	private final BeanFieldGroup<Car> binder;
	
	public CarEditPage() {
		super();
		carForm = new CarForm();
		carForm.addSaveButtonListener(e -> handleSave());
		carForm.addRemoveButtonListener(e -> handleRemove());
		carForm.addDiscardButtonListener(e -> handleDiscard());
		binder = new BeanFieldGroup<>(Car.class);
		this.setContent(carForm);
		this.setContentWidth(850, Unit.PIXELS);
		this.alignCenterContent();
		this.setContentBorderless();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		checkPermissions();
		final String parameters = event.getParameters();
		final Car car = extractCarFromParameters(parameters);
		open(car);	
	}

	private void checkPermissions() {
		
	}
	
	private void open(Car car) {
		if (car == null) {
			car = new Car();
		}
		binder.discard();
		binder.bindMemberFields(carForm);
		binder.setItemDataSource(car);
		carForm.getImagesComponent().setImages(Arrays.asList("https://data.motor-talk.de/data/galleries/0/160/2907/69570746/url-3010870319480993900-7706099403658557496.jpg", 
				"https://img.bmw-syndikat.de/gallery/196/502/943027_bmw-syndikat_bild_high.jpg",
				"https://img.bmw-syndikat.de/gallery/196/502/943027_bmw-syndikat_bild_high.jpg",
				"https://img.bmw-syndikat.de/gallery/196/502/943027_bmw-syndikat_bild_high.jpg",
				"https://img.bmw-syndikat.de/gallery/196/502/943027_bmw-syndikat_bild_high.jpg"));
	}
	
	private void handleDiscard() {
		binder.discard();
	}

	private void handleRemove() {
		final Car car = binder.getItemDataSource().getBean();
		if (car != null && car.getId() != null) {
			try {
				dataService.removeCar(car.getId());
				binder.discard();
			} catch (DataDoesNotExistException e) {
				Notification.show("Cannot remove element!");
			}
		}
	}

	private void handleSave() {
		if (!binder.isValid()) {
			Notification.show("Cannot save data!");
		}
		try {
			binder.commit();
		} catch (CommitException e) {
			Notification.show("Cannot save data!");
		}
		final Car car = binder.getItemDataSource().getBean();
		dataService.saveCar(car);
	}
	
	private Car extractCarFromParameters(final String parameters) {
		Car car = null;
		if (parameters == null || parameters.isEmpty()) {
			return car;
		} 
		try {
			final Long carId = Long.valueOf(parameters);
			car = dataService.getCar(carId);
		} catch (NumberFormatException | DataDoesNotExistException e) {
			// do nothing	
		}
		return car;
	}
}
