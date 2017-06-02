package rgeoroceanu.cms.layout;

import javax.persistence.Basic;

import org.springframework.stereotype.Component;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import rgeoroceanu.cms.component.form.Form;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.business.Dealership;

/**
 * Layout associated with a {@link Dealership} entity for creating, editing or deleting it.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
public class DealershipEditLayout extends VerticalLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	
	@Basic
	private String name;
	@Basic
	private String address;
	@Basic
	private String city;
	@Basic
	private String country;
	@Basic
	private String zip;
	@Basic
	private String phone;
	@Basic
	private String email;
	
	private final @PropertyId("name") TextField nameField;
	private final @PropertyId("address") TextField addressField;
	private final @PropertyId("city") TextField cityField;
	private final @PropertyId("country") TextField countryField;
	private final @PropertyId("zip") TextField zipField;
	private final @PropertyId("phone") TextField phoneField;
	private final @PropertyId("email") TextField emailField;
	private final Panel panel;
	private final Form layout;
	
	public DealershipEditLayout() {
		nameField = initTextField();
		addressField = initTextField();
		cityField = initTextField();
		countryField = initTextField();
		zipField = initTextField();
		phoneField = initTextField();
		emailField = initTextField();
		layout = new Form();
		layout.setActionButtonsEnableState(true, true, false);
		panel = new Panel();
		
		setupLayout();
		
		layout.addComponent(panel);
		this.addComponent(layout);
	}
	
	@Override
	public void localize() {
		nameField.setCaption(Localizer.getLocalizedString("name"));
		cityField.setCaption(Localizer.getLocalizedString("city"));
		addressField.setCaption(Localizer.getLocalizedString("address"));
		countryField.setCaption(Localizer.getLocalizedString("country"));
		zipField.setCaption(Localizer.getLocalizedString("zip"));
		phoneField.setCaption(Localizer.getLocalizedString("phone"));
		emailField.setCaption(Localizer.getLocalizedString("email"));
		panel.setCaption(Localizer.getLocalizedString("general"));
	}
	
	public void addSaveButtonListener(final ClickListener listener) {
		layout.addSaveButtonListener(listener);
	}
	
	public void addDiscardButtonListener(final ClickListener listener) {
		layout.addDiscardButtonListener(listener);
	}
	
	private void setupLayout() {
		final HorizontalLayout layout = new HorizontalLayout();
		final FormLayout formLayout1 = new FormLayout();
		final FormLayout formLayout2 = new FormLayout();
		formLayout1.addComponent(nameField);
		formLayout2.addComponent(new Label());
		formLayout1.addComponent(addressField);
		formLayout2.addComponent(cityField);
		formLayout1.addComponent(zipField);
		formLayout2.addComponent(countryField);
		formLayout1.addComponent(phoneField);
		formLayout2.addComponent(emailField);
		formLayout1.setSpacing(true);
		formLayout1.setMargin(true);
		formLayout2.setSpacing(true);
		formLayout2.setMargin(true);
		layout.addComponent(formLayout1);
		layout.addComponent(formLayout2);
		panel.setContent(layout);
	}
	
	private TextField initTextField() {
		final TextField field = new TextField();
		field.setNullRepresentation("");
		return field;
	}
}
