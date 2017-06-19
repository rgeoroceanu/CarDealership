package rgeoroceanu.cms.layout;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.vaadin.annotations.PropertyId;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import lombok.Getter;
import rgeoroceanu.cms.component.form.Form;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.business.User;
import rgeoroceanu.model.type.Role;

/**
 * Layout associated with a {@link User} entity for creating, editing or deleting it.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Getter
@Component
public class UserEditLayout extends PageLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	
	private final @PropertyId("username") TextField usernameField;
	private final @PropertyId("password") PasswordField passwordField;
	private final @PropertyId("roles") ListSelect<Role> rolesField;
	private final Panel panel;
	private final Form layout;
	
	public UserEditLayout() {
		usernameField = initUsernameField();
		passwordField = initPasswordField();
		rolesField = initRolesField();
		layout = new Form();
		panel = new Panel();
		
		setupLayout();
		
		layout.addComponent(panel);
		layout.setSpacing(true);
		this.setContent(layout);
	}
	
	@Override
	public void localize() {
		super.localize();
		usernameField.setCaption(Localizer.getLocalizedString("username"));
		passwordField.setCaption(Localizer.getLocalizedString("password"));
		rolesField.setCaption(Localizer.getLocalizedString("roles"));
		panel.setCaption(Localizer.getLocalizedString("general"));
	}
	
	public void addSaveButtonListener(final ClickListener listener) {
		layout.addSaveButtonListener(listener);
	}
	
	public void addRemoveButtonListener(final ClickListener listener) {
		layout.addRemoveButtonListener(listener);
	}
	
	public void addDiscardButtonListener(final ClickListener listener) {
		layout.addDiscardButtonListener(listener);
	}
	
	private void setupLayout() {
		final FormLayout formLayout = new FormLayout();
		formLayout.addComponent(usernameField);
		formLayout.addComponent(passwordField);
		formLayout.addComponent(rolesField);
		formLayout.setSpacing(true);
		formLayout.setMargin(true);
		panel.setContent(formLayout);
	}
	
	private TextField initUsernameField() {
		final TextField field = new TextField();
		field.setWidth(400, Unit.PIXELS);
		return field;
	}
	
	private PasswordField initPasswordField() {
		final PasswordField field = new PasswordField();
		field.setRequiredIndicatorVisible(true);
		field.setWidth(400, Unit.PIXELS);
		return field;
	}
	
	private ListSelect<Role> initRolesField() {
		final ListSelect<Role> field = new ListSelect<Role>();
		field.setItems(Arrays.asList(Role.values()));
		field.setWidth(400, Unit.PIXELS);
		return field;
	}
}
