package rgeoroceanu.cardealership.cms.page;

import org.springframework.stereotype.Component;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Binder;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;

import rgeoroceanu.cardealership.cms.App;
import rgeoroceanu.cardealership.cms.layout.UserEditLayout;
import rgeoroceanu.cardealership.cms.localization.Localizer;
import rgeoroceanu.cardealership.model.business.Car;
import rgeoroceanu.cardealership.model.business.User;
import rgeoroceanu.cardealership.service.exception.DataDoesNotExistException;

/**
 * Page used for editing and adding of {@link Car} entities.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
@UIScope
@SpringView
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
		super.enter(event);
		if (App.getCurrent().isAdmin() == false) {
			App.getCurrent().navigateToErrorPage("Permission denied!");
			return;
		}
		final String parameters = event.getParameters();
		final User user = extractUserFromParameters(parameters);
		open(user);	
	}

	private void bindFields() {
		binder.forField(userEditLayout.getUsernameField())
			.asRequired("Please provide username!")
			.withValidator(username -> username != null && username.length() > 6, 
					"Username must be longer then 6 characters")
			.bind("username");
		binder.forField(userEditLayout.getPasswordField())
			.asRequired("Please provide password!")
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
		final User user = binder.getBean();
		if (user != null && user.getId() != null) {
			ConfirmDialog.show(this.getUI(), 
					Localizer.getLocalizedString("delete"), 
					Localizer.getLocalizedString("confirm_remove_message"), 
					Localizer.getLocalizedString("delete"), 
					Localizer.getLocalizedString("cancel"), confirmEvent -> {
						if (confirmEvent.isConfirmed()) {
							confirmRemove(user);
						}
					});
		}
	}

	private void handleSave() {
		if (binder.isValid() == false) {
			Notification.show(Localizer.getLocalizedString("error_save_data"));
			return;
		} 
		String previousPassword = null;
		final Long userId = binder.getBean().getId();
		try {
			previousPassword = dataService.getUser(userId).getPassword();
		} catch (NullPointerException | DataDoesNotExistException e) {
			previousPassword = null;
		}
				
		boolean encodePassword = false;
		final User user = binder.getBean();
		if (previousPassword == null || previousPassword.equals(user.getPassword()) == false) {
			encodePassword = true;
		} 
		
		dataService.saveUser(user, encodePassword);
		App.getCurrent().navigateToStartPage();
		Notification.show(Localizer.getLocalizedString("saved"));
	}

	private void confirmRemove(final User user) {
		try {
			// remove entity
			dataService.removeUser(user.getId());

		} catch (DataDoesNotExistException e) {
			Notification.show(Localizer.getLocalizedString("error_remove_element"));
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
	
	private void confirmDiscard() {
		User original;
		try {
			original = dataService.getUser(binder.getBean().getId());
		} catch (DataDoesNotExistException e) {
			original = new User();
		}
		binder.readBean(original);	
	}
}
