package rgeoroceanu.cms.page;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.converter.ColorToCodeConverter;
import rgeoroceanu.cms.layout.CarEditLayout;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.business.Car;
import rgeoroceanu.model.business.Purchase;
import rgeoroceanu.service.exception.DataDoesNotExistException;
import rgeoroceanu.service.exception.ImageDeleteException;
import rgeoroceanu.service.exception.ImageWriteException;

/**
 * Page used for editing and adding of {@link Car} entities.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
@UIScope
@SpringView
public class CarEditPage extends Page {

	private static final long serialVersionUID = 1L;
	private final CarEditLayout carEditLayout;
	private final Binder<Car> binder;

	public CarEditPage() {
		super();
		carEditLayout = new CarEditLayout();
		carEditLayout.addSaveButtonListener(e -> handleSave());
		carEditLayout.addRemoveButtonListener(e -> handleRemove());
		carEditLayout.addDiscardButtonListener(e -> handleDiscard());
		carEditLayout.addSoldButtonListener(e -> handleSold());
		binder = new Binder<>(Car.class);
		bindFields();
		carEditLayout.setContentWidth(950, Unit.PIXELS);
		carEditLayout.alignCenterContent();
		carEditLayout.setContentBorderless();
		this.setLayout(carEditLayout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		final String parameters = event.getParameters();
		final Car car = extractCarFromParameters(parameters);
		open(car);	
	}

	private void bindFields() {
		binder.forField(carEditLayout.getMarqueField())
			.asRequired("Please provide car make!")
			.bind(Car::getMake, Car::setMake);
		binder.forField(carEditLayout.getModelField())
			.asRequired("Please provide car model!")
			.bind(Car::getModel, Car::setModel);;
		binder.forField(carEditLayout.getRegistrationDateField())
			.asRequired("Please provide registration date!")
			.bind(Car::getRegistrationDate, Car::setRegistrationDate);;
		binder.forField(carEditLayout.getKilometersField())
			.withConverter(new StringToIntegerConverter("Number required"))
			.bind(Car::getKilometers, Car::setKilometers);
		binder.forField(carEditLayout.getHorsePowerField())
			.withConverter(new StringToIntegerConverter("Number required"))
			.bind(Car::getHorsePower, Car::setHorsePower);
		binder.forField(carEditLayout.getCubicCapacityField())
			.withConverter(new StringToIntegerConverter("Number required"))
			.bind(Car::getCubicCentimeters, Car::setCubicCentimeters);
		binder.forField(carEditLayout.getOriginalPriceField())
			.withConverter(new StringToIntegerConverter("Number required"))
			.bind(Car::getOriginalPrice, Car::setOriginalPrice);
		binder.forField(carEditLayout.getDiscountedPriceField())
			.withConverter(new StringToIntegerConverter("Number required"))
			.bind(Car::getDiscountedPrice, Car::setDiscountedPrice);
		binder.forField(carEditLayout.getColorField())
			.withConverter(new ColorToCodeConverter())
			.bind(Car::getColor, Car::setColor);
		binder.bindInstanceFields(carEditLayout);
	}

	private void open(Car car) {
		if (car == null) {
			car = new Car();
		}
		binder.setBean(car);
		List<String> imageUrls = new ArrayList<>();
		if (car.getId() != null) {
			imageUrls.addAll(imageService.getPreviewImageUrls(car.getId()));
			carEditLayout.setActionButtonsEnableState(true, true, true, true);
		} else  {
			carEditLayout.setActionButtonsEnableState(true, true, false, false);
		}
		carEditLayout.getImagesComponent().setImages(imageUrls);
	}

	private void handleDiscard() {
		ConfirmDialog.show(this.getUI(), 
				Localizer.getLocalizedString("discard"), 
				Localizer.getLocalizedString("confirm_discard_message"), 
				Localizer.getLocalizedString("discard"), 
				Localizer.getLocalizedString("cancel"), confirmEvent -> {
					if (confirmEvent.isConfirmed()) {
						confirmDiscard();
					}
				});
	}

	private void handleRemove() {
		final Car car = binder.getBean();
		if (car != null && car.getId() != null) {
			ConfirmDialog.show(this.getUI(), 
					Localizer.getLocalizedString("delete"), 
					Localizer.getLocalizedString("confirm_remove_message"), 
					Localizer.getLocalizedString("delete"), 
					Localizer.getLocalizedString("cancel"), confirmEvent -> {
						if (confirmEvent.isConfirmed()) {
							confirmRemove(car);
						}
					});
		}
	}

	private void handleSave() {
		if (binder.isValid() == false) {
			Notification.show(Localizer.getLocalizedString("error_save_data"));
			return;
		}
		final Car car = binder.getBean();
		dataService.saveCar(car);

		try {
			// save images
			for (final File uploaded : carEditLayout.getImagesComponent().getUploadedImageFiles()) {
				imageService.saveImages(uploaded, car.getId());
			}
		} catch (ImageWriteException e) {
			Notification.show(Localizer.getLocalizedString("error_save_images"));
		}

		try {
			// remove images
			for (final String removedUrl : carEditLayout.getImagesComponent().getRemovedImageUrls()) {
				final String filename = removedUrl.substring(removedUrl.lastIndexOf("/") +1 , removedUrl.length());
				imageService.deleteImage(filename, car.getId());
			}
		} catch (ImageDeleteException e) {
			Notification.show(Localizer.getLocalizedString("error_remove_images"));
		}
		App.getCurrent().navigateToStartPage();
	}

	private void confirmRemove(final Car car) {
		try {
			// remove entity
			dataService.removeCar(car.getId());

		} catch (DataDoesNotExistException e) {
			Notification.show(Localizer.getLocalizedString("error_remove_element"));
		}

		// reinitialize binder
		binder.readBean(binder.getBean());

		// remove images
		try {
			imageService.deleteAllImages(car.getId());
		} catch (ImageDeleteException e) {
			// do nothing
		}
		// navigate to home page
		App.getCurrent().navigateToStartPage();
		Notification.show(Localizer.getLocalizedString("saved"));
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
	
	private void handleSold() {
		ConfirmDialog.show(this.getUI(), 
				Localizer.getLocalizedString("confirm_sold_title"), 
				Localizer.getLocalizedString("confirm_sold_message"), 
				Localizer.getLocalizedString("sold"), 
				Localizer.getLocalizedString("cancel"), confirmEvent -> {
					if (confirmEvent.isConfirmed()) {
						confirmSold();
					}
				});
	}
	
	private void confirmDiscard() {
		Car original;
		try {
			original = dataService.getCar(binder.getBean().getId());
		} catch (DataDoesNotExistException e) {
			original = new Car();
		}
		binder.readBean(original);	
	}
	
	private void confirmSold() {
		final Car car = binder.getBean();
		if (car.getId() == null) {
			return;
		}
		final Purchase purchase = car.createPurchase();
		dataService.savePurchase(purchase);
		try {
			dataService.removeCar(car.getId());
		} catch (DataDoesNotExistException e) {
			// checked before
		}
	}
}
