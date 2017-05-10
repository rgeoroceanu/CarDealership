package rgeoroceanu.cms.layout;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;

public class CarOverview extends Panel implements Localizable {
	
	private static final long serialVersionUID = 1L;
	private final HorizontalLayout imageField;
	private final Label titleField;
	private final Label subtitleField;
	private final Label discountedPriceField;
	private final Label originalPriceField;
	private final Label stateField;
	private final Label registrationDateField;
	private final Label engineField;
	private final Label cubicCapacityField;
	private final Label transmissionField;
	private final Label doorsField;
	private final Label horsePowerField;
	
	public CarOverview() {
		imageField = initImageField();
		titleField = initTitleLabel();
		subtitleField = initSubtitleLabel();
		discountedPriceField = initTitleLabel();
		originalPriceField = initSubtitleLabel();
		stateField = initPropertyLabel();
		registrationDateField = initPropertyLabel();
		engineField = initPropertyLabel();
		cubicCapacityField = initPropertyLabel();
		transmissionField = initPropertyLabel();
		doorsField = initPropertyLabel();
		horsePowerField = initPropertyLabel();
		final GridLayout headerLayout = initHeader();
		final VerticalLayout infoLayout = new VerticalLayout();
		final HorizontalLayout propertiesLayout = initPropertiesLayout();
		infoLayout.addComponent(headerLayout);
		infoLayout.addComponent(propertiesLayout);
		final HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(imageField);
		layout.addComponent(infoLayout);
		this.setContent(layout);
	}
	
	public void setTitle(final String title) {
		this.titleField.setValue(title);
	}
	
	public void setSubtitle(final String subtitle) {
		this.subtitleField.setValue(subtitle);
	}
	
	public void setPrice(final String discountedPrice, final String originalPrice) {
		this.discountedPriceField.setValue(discountedPrice);
		this.originalPriceField.setValue(originalPrice);
	}
	
	public void setDetails(final String state, final String registrationDate, 
			final String engine, final String cubicCapacity, final String transmission, 
			final String horsePower, final String doors) {
		
		this.stateField.setValue(state);
		this.registrationDateField.setValue(registrationDate);
		this.doorsField.setValue(doors);
		this.engineField.setValue(engine);
		this.cubicCapacityField.setValue(cubicCapacity);
		this.transmissionField.setValue(transmission);
		this.horsePowerField.setValue(horsePower);
	}
	
	private GridLayout initHeader() {
		final GridLayout headerLayout = new GridLayout(2, 2);
		headerLayout.addComponent(titleField, 0, 0);
		headerLayout.addComponent(subtitleField, 0, 1);
		headerLayout.addComponent(discountedPriceField, 1, 0);
		headerLayout.addComponent(originalPriceField, 1, 1);
		return headerLayout;
	}
	
	private HorizontalLayout initPropertiesLayout() {
		final HorizontalLayout propertiesLayout = new HorizontalLayout();
		final FormLayout propertiesFormLayout1 = new FormLayout();
		final FormLayout propertiesFormLayout2 = new FormLayout();
		propertiesFormLayout1.addComponent(stateField);
		propertiesFormLayout2.addComponent(registrationDateField);
		propertiesFormLayout1.addComponent(engineField);
		propertiesFormLayout2.addComponent(cubicCapacityField);
		propertiesFormLayout1.addComponent(transmissionField);
		propertiesFormLayout2.addComponent(horsePowerField);
		propertiesFormLayout1.addComponent(doorsField);
		propertiesLayout.addComponent(propertiesFormLayout1);
		propertiesLayout.addComponent(propertiesFormLayout2);
		return propertiesLayout;
	}
	
	private HorizontalLayout initImageField() {
		final HorizontalLayout imageField = new HorizontalLayout();
		imageField.setWidth(200, Unit.PIXELS);
		imageField.setHeight(200, Unit.PIXELS);
		return imageField;
	}
	
	private Label initTitleLabel() {
		final Label label = new Label();
		label.addStyleName(ValoTheme.LABEL_H2);
		return label;
	}
	
	private Label initSubtitleLabel() {
		final Label label = new Label();
		label.addStyleName(ValoTheme.LABEL_H3);
		return label;
	}
	
	private Label initPropertyLabel() {
		final Label label = new Label();
		return label;
	}

	@Override
	public void localize() {
		this.stateField.setCaption(Localizer.getLocalizedString("state"));
		this.registrationDateField.setCaption(Localizer.getLocalizedString("registration_date"));
		this.doorsField.setCaption(Localizer.getLocalizedString("doors"));
		this.engineField.setCaption(Localizer.getLocalizedString("engine"));
		this.cubicCapacityField.setCaption(Localizer.getLocalizedString("cubic_capacity"));
		this.transmissionField.setCaption(Localizer.getLocalizedString("transmission"));
		this.horsePowerField.setCaption(Localizer.getLocalizedString("horse_power"));
	}
}
