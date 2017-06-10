package rgeoroceanu.cms.page;

import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.layout.DealershipEditLayout;
import rgeoroceanu.model.business.Dealership;
import rgeoroceanu.service.exception.DataDoesNotExistException;

/**
 * Page used for managing users and general dealership information.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
public class DealershipEditPage extends Page {

	private static final long serialVersionUID = 1L;
	private final DealershipEditLayout dealershipLayout;
	private final Binder<Dealership> dealershipBinder;

	public DealershipEditPage() {
		super();
		dealershipLayout = new DealershipEditLayout();
		dealershipBinder = new Binder<>(Dealership.class);
		dealershipBinder.bindInstanceFields(dealershipLayout);
		dealershipLayout.addSaveButtonListener(e -> handleSave());
		dealershipLayout.addDiscardButtonListener(e -> handleDiscard());
		dealershipLayout.setContentWidth(950, Unit.PIXELS);
		dealershipLayout.alignCenterContent();
		dealershipLayout.setContentBorderless();
		this.setLayout(dealershipLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		checkPermissions();
		open();
	}

	private void checkPermissions() {

	}
	
	private void open() {
		Dealership dealership;
		try {
			dealership = dataService.getDealership();
		} catch (DataDoesNotExistException e) {
			dealership = new Dealership();
		}
		dealershipBinder.setBean(dealership);
	}

	private void handleDiscard() {
		ConfirmDialog.show(this.getUI(), "Discard", 
				"Are you sure you want to discard all changes?", 
				"Discard", "Cancel", confirmEvent -> {
					dealershipBinder.readBean(dealershipBinder.getBean());
				});
	}

	private void handleSave() {
		try {
			dealershipBinder.writeBean(dealershipBinder.getBean());
		} catch (ValidationException e) {
			Notification.show("Cannot save data!");
			return;
		}
		final Dealership dealership = dealershipBinder.getBean();
		dataService.saveDealership(dealership);
		Notification.show("Saved!");
		App.getCurrent().navigateToStartPage();
	}

}
