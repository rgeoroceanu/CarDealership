package rgeoroceanu.cms.page;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.layout.CarEditLayout;
import rgeoroceanu.model.business.Car;
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
public class CarEditPage extends Page {

	private static final long serialVersionUID = 1L;
	private final CarEditLayout carEditLayout;
	private final BeanFieldGroup<Car> binder;

	public CarEditPage() {
		super();
		carEditLayout = new CarEditLayout();
		carEditLayout.addSaveButtonListener(e -> handleSave());
		carEditLayout.addRemoveButtonListener(e -> handleRemove());
		carEditLayout.addDiscardButtonListener(e -> handleDiscard());
		binder = new BeanFieldGroup<>(Car.class);
		carEditLayout.setContentWidth(850, Unit.PIXELS);
		carEditLayout.alignCenterContent();
		carEditLayout.setContentBorderless();
		this.setLayout(carEditLayout);
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
		binder.bindMemberFields(carEditLayout);
		binder.setItemDataSource(car);
		List<String> imageUrls = new ArrayList<>();
		if (car.getId() != null) {
			imageUrls.addAll(imageService.getPreviewImageUrls(car.getId()));
		} 
		carEditLayout.getImagesComponent().setImages(imageUrls);
	}

	private void handleDiscard() {
		ConfirmDialog.show(this.getUI(), "Discard", 
				"Are you sure you want to discard all changes?", 
				"Discard", "Cancel", confirmEvent -> {
					binder.discard();
				});
	}

	private void handleRemove() {
		final Car car = binder.getItemDataSource().getBean();
		if (car != null && car.getId() != null) {
			ConfirmDialog.show(this.getUI(), "Delete", 
					"Are you sure you want to delete this car?", 
					"Delete", "Cancel", confirmEvent -> {
						if (confirmEvent.isConfirmed()) {
							confirmRemove(car);
						}
					});
		}
	}

	private void handleSave() {
		if (!binder.isValid()) {
			Notification.show("Cannot save data!");
			return;
		}
		try {
			binder.commit();
		} catch (CommitException e) {
			Notification.show("Cannot save data!");
			return;
		}
		final Car car = binder.getItemDataSource().getBean();
		dataService.saveCar(car);

		try {
			// save images
			for (final File uploaded : carEditLayout.getImagesComponent().getUploadedImageFiles()) {
				imageService.saveImages(uploaded, car.getId());
			}
		} catch (ImageWriteException e) {
			Notification.show("Images were not saved!");
		}

		try {
			// remove images
			for (final String removedUrl : carEditLayout.getImagesComponent().getRemovedImageUrls()) {
				final String filename = removedUrl.substring(removedUrl.lastIndexOf("/") +1 , removedUrl.length());
				imageService.deleteImage(filename, car.getId());
			}
		} catch (ImageDeleteException e) {
			Notification.show("Images were not removed!");
		}
		App.getCurrent().navigateToStartPage();
	}

	private void confirmRemove(final Car car) {
		try {
			// remove entity
			dataService.removeCar(car.getId());

		} catch (DataDoesNotExistException e) {
			Notification.show("Cannot remove element!");
		}
		
		// reinitialize binder
		binder.discard();

		// remove images
		try {
			imageService.deleteAllImages(car.getId());
		} catch (ImageDeleteException e) {
			// do nothing
		}
		// navigate to home page
		App.getCurrent().navigateToStartPage();
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
