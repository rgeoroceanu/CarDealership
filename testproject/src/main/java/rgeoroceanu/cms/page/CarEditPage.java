package rgeoroceanu.cms.page;

import org.springframework.stereotype.Component;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import rgeoroceanu.cms.component.form.CarForm;
import rgeoroceanu.cms.component.form.Form.DiscardButtonListener;
import rgeoroceanu.cms.component.form.Form.RemoveButtonListener;
import rgeoroceanu.cms.component.form.Form.SaveButtonListener;
import rgeoroceanu.model.Car;
import rgeoroceanu.service.exception.DataDoesNotExistException;

@Component
public class CarEditPage extends Page implements SaveButtonListener, RemoveButtonListener, DiscardButtonListener {

	private static final long serialVersionUID = 1L;
	private final CarForm carForm;
	private final BeanFieldGroup<Car> binder;
	
	public CarEditPage() {
		super();
		carForm = new CarForm();
		carForm.setSaveButtonListener(this);
		carForm.setRemoveButtonListener(this);
		carForm.setDiscardButtonListener(this);
		binder = new BeanFieldGroup<>(Car.class);
		this.setContent(carForm);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		checkPermissions();
		final String parameters = event.getParameters();
		final Car car = extractCarFromParameters(parameters);
		open(car);	
	}

	@Override
	public void handleDiscard() {
		binder.discard();
	}

	@Override
	public void handleRemove() {
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

	@Override
	public void handleSave() {
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
	
	private void checkPermissions() {
		
	}
	
	private void open(Car car) {
		if (car == null) {
			car = new Car();
		}
		binder.discard();
		binder.bindMemberFields(carForm);
		binder.setItemDataSource(car);
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
