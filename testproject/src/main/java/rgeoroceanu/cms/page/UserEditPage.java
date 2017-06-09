package rgeoroceanu.cms.page;

import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.layout.UserEditLayout;
import rgeoroceanu.model.business.Car;
import rgeoroceanu.model.business.User;
import rgeoroceanu.service.exception.DataDoesNotExistException;

/**
 * Page used for editing and adding of {@link Car} entities.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
public class UserEditPage extends Page {

	private static final long serialVersionUID = 1L;
	private final UserEditLayout userEditLayout;
	private final BeanFieldGroup<User> binder;

	public UserEditPage() {
		super();
		userEditLayout = new UserEditLayout();
		userEditLayout.addSaveButtonListener(e -> handleSave());
		userEditLayout.addRemoveButtonListener(e -> handleRemove());
		userEditLayout.addDiscardButtonListener(e -> handleDiscard());
		binder = new BeanFieldGroup<>(User.class);
		userEditLayout.setContentWidth(850, Unit.PIXELS);
		userEditLayout.alignCenterContent();
		userEditLayout.setContentBorderless();
		this.setLayout(userEditLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		checkPermissions();
		final String parameters = event.getParameters();
		final User user = extractUserFromParameters(parameters);
		open(user);	
	}

	private void checkPermissions() {

	}

	private void open(User user) {
		if (user == null) {
			user = new User();
		}
		binder.discard();
		binder.bindMemberFields(userEditLayout);
		binder.setItemDataSource(user);
	}

	private void handleDiscard() {
		ConfirmDialog.show(this.getUI(), "Discard", 
				"Are you sure you want to discard all changes?", 
				"Discard", "Cancel", confirmEvent -> {
					binder.discard();
				});
	}

	private void handleRemove() {
		final User user = binder.getItemDataSource().getBean();
		if (user != null && user.getId() != null) {
			ConfirmDialog.show(this.getUI(), "Delete", 
					"Are you sure you want to delete this car?", 
					"Delete", "Cancel", confirmEvent -> {
						if (confirmEvent.isConfirmed()) {
							confirmRemove(user);
						}
					});
		}
	}

	private void handleSave() {
		final User previous = binder.getItemDataSource().getBean();
		final String previousPassword = previous.getPassword();
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
		
		boolean encodePassword = false;
		final User user = binder.getItemDataSource().getBean();
		if (previousPassword == null || previousPassword.equals(user.getPassword()) == false) {
			encodePassword = true;
		} 
		
		dataService.saveUser(user, encodePassword);
		App.getCurrent().navigateToStartPage();
	}

	private void confirmRemove(final User user) {
		try {
			// remove entity
			dataService.removeUser(user.getId());

		} catch (DataDoesNotExistException e) {
			Notification.show("Cannot remove element!");
		}
		
		// reinitialize binder
		binder.discard();
		// navigate to home page
		App.getCurrent().navigateToStartPage();
	}

	private User extractUserFromParameters(final String parameters) {
		User user = null;
		
		if (parameters == null || parameters.isEmpty()) {
			return user;
		} 
		
		try {
			final Long userId = Long.valueOf(parameters);
			user = dataService.getUser(userId);
		} catch (NumberFormatException | DataDoesNotExistException e) {
			// do nothing	
		}
		
		return user;
	}
}
