package rgeoroceanu.cms.page;

import java.util.List;

import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.layout.ManagerLayout;
import rgeoroceanu.model.business.Dealership;
import rgeoroceanu.model.business.User;
import rgeoroceanu.service.exception.DataDoesNotExistException;

/**
 * Page used for managing users and general dealership information.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
public class ManagerPage extends Page {

	private static final long serialVersionUID = 1L;
	private final ManagerLayout managerLayout;
	private final BeanFieldGroup<Dealership> dealershipBinder;
	private final BeanItemContainer<User> usersContainer;

	public ManagerPage() {
		super();
		managerLayout = new ManagerLayout();
		dealershipBinder = new BeanFieldGroup<>(Dealership.class);
		usersContainer = new BeanItemContainer<>(User.class);
		managerLayout.setUsersContainer(usersContainer);
		managerLayout.setVisibleColumns(new Object[] {"username", "roles"});
		managerLayout.addSaveDealershipButtonListener(e -> handleSave());
		managerLayout.addDiscardDealershipButtonListener(e -> handleDiscard());
		managerLayout.addUserClickListener(e -> App.getCurrent().navigateToUserPage(((User) e.getItemId()).getId()));
		this.setLayout(managerLayout);
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
		dealershipBinder.bindMemberFields(managerLayout.getDealershipEditLayout());
		dealershipBinder.setItemDataSource(dealership);
		final List<User> users = dataService.getAllUsers();
		usersContainer.removeAllItems();
		usersContainer.addAll(users);
		
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
		}
		try {
			dealershipBinder.commit();
		} catch (CommitException e) {
			Notification.show("Cannot save data!");
		}
		final Dealership dealership = dealershipBinder.getItemDataSource().getBean();
		dataService.saveDealership(dealership);
		Notification.show("Saved!");
	}

}
