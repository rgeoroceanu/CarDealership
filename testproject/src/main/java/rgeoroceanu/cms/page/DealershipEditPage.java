package rgeoroceanu.cms.page;

import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

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
	private final BeanFieldGroup<Dealership> dealershipBinder;

	public DealershipEditPage() {
		super();
		dealershipLayout = new DealershipEditLayout();
		dealershipBinder = new BeanFieldGroup<>(Dealership.class);
		dealershipLayout.addSaveButtonListener(e -> handleSave());
		dealershipLayout.addDiscardButtonListener(e -> handleDiscard());
		dealershipLayout.setContentWidth(850, Unit.PIXELS);
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
		dealershipBinder.discard();
		dealershipBinder.bindMemberFields(dealershipLayout);
		dealershipBinder.setItemDataSource(dealership);
	}

	private void handleDiscard() {
		ConfirmDialog.show(this.getUI(), "Discard", 
				"Are you sure you want to discard all changes?", 
				"Discard", "Cancel", confirmEvent -> {
					dealershipBinder.discard();
				});
	}

	private void handleSave() {
		if (!dealershipBinder.isValid()) {
			Notification.show("Cannot save data!");
			return;
		}
		try {
			dealershipBinder.commit();
		} catch (CommitException e) {
			Notification.show("Cannot save data!");
			return;
		}
		final Dealership dealership = dealershipBinder.getItemDataSource().getBean();
		dataService.saveDealership(dealership);
		Notification.show("Saved!");
	}

}
