package rgeoroceanu.cms.page;

import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.layout.DealershipEditLayout;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.business.Dealership;
import rgeoroceanu.service.exception.DataDoesNotExistException;

/**
 * Page used for managing users and general dealership information.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
@UIScope
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

	private void handleSave() {
		if (dealershipBinder.isValid() == false) {
			Notification.show(Localizer.getLocalizedString("error_save_data"));
			return;
		}
		final Dealership dealership = dealershipBinder.getBean();
		dataService.saveDealership(dealership);
		Notification.show(Localizer.getLocalizedString("saved"));
		App.getCurrent().navigateToStartPage();
	}

	private void confirmDiscard() {
		Dealership original;
		try {
			original = dataService.getDealership();
		} catch (DataDoesNotExistException e) {
			original = new Dealership();
		}
		dealershipBinder.readBean(original);
	}
	
}
