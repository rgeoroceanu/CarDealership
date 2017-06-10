package rgeoroceanu.cms.page;

import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
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
	private final Binder<User> binder;

	public UserEditPage() {
		super();
		userEditLayout = new UserEditLayout();
		userEditLayout.addSaveButtonListener(e -> handleSave());
		userEditLayout.addRemoveButtonListener(e -> handleRemove());
		userEditLayout.addDiscardButtonListener(e -> handleDiscard());
		binder = new Binder<>(User.class);
		bindFields();
		userEditLayout.setContentWidth(950, Unit.PIXELS);
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
	
	private void bindFields() {
		binder.forField(userEditLayout.getUsernameField())
			.withValidator(username -> username != null && username.length() > 6, 
					"Username must be longer then 6 characters")
			.bind("username");
		binder.forField(userEditLayout.getPasswordField())
			.withValidator(password -> password != null && password.length() > 6, 
					"Password must be longer then 6 characters")
			.bind("password");
		binder.bindInstanceFields(userEditLayout);
	}
	
	private void open(User user) {
		if (user == null) {
			user = new User();
		}
		binder.setBean(user);
	}

	private void handleDiscard() {
		ConfirmDialog.show(this.getUI(), "Discard", 
				"Are you sure you want to discard all changes?", 
				"Discard", "Cancel", confirmEvent -> {
					binder.readBean(binder.getBean());
				});
	}

	private void handleRemove() {
		final User user = binder.getBean();
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
		final User previous = binder.getBean();
		final String previousPassword = previous.getPassword();
		try {
			binder.writeBean(binder.getBean());
		} catch (ValidationException e) {
			Notification.show("Cannot save data!");
			return;
		}
		
		boolean encodePassword = false;
		final User user = binder.getBean();
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
		binder.readBean(binder.getBean());
		// navigate to home page
		App.getCurrent().navigateToUsersPage();
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
