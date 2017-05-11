package rgeoroceanu.cms.layout;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;

public class CarOverview extends Panel implements Localizable {
	
	private static final long serialVersionUID = 1L;
	private final Image imageField;
	private final Label titleField;
	private final Label subtitleField;
	private final Label discountedPriceField;
	private final Label stateField;
	private final Label registrationDateField;
	private final Label engineField;
	private final Label cubicCapacityField;
	private final Label transmissionField;
	private final Label horsePowerField;
	
	public CarOverview() {
		imageField = initImageField();
		titleField = initTitleLabel();
		subtitleField = initSubtitleLabel();
		discountedPriceField = initTitleLabel();
		stateField = initPropertyLabel();
		registrationDateField = initPropertyLabel();
		engineField = initPropertyLabel();
		cubicCapacityField = initPropertyLabel();
		transmissionField = initPropertyLabel();
		horsePowerField = initPropertyLabel();
		final HorizontalLayout headerLayout = initHeader();
		final VerticalLayout infoLayout = new VerticalLayout();
		final HorizontalLayout propertiesLayout = initPropertiesLayout();
		infoLayout.addComponent(headerLayout);
		infoLayout.addComponent(subtitleField);
		infoLayout.addComponent(propertiesLayout);
		infoLayout.setSpacing(false);
		infoLayout.setMargin(false);
		final HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(imageField);
		layout.addComponent(infoLayout);
		layout.setExpandRatio(infoLayout, 1);
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setSpacing(false);
		layout.setMargin(true);
		this.setWidth(700, Unit.PIXELS);
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
	}
	
	public void setDetails(final String state, final String registrationDate, 
			final String engine, final String cubicCapacity, final String transmission, 
			final String horsePower, final String doors) {
		
		this.stateField.setValue(state);
		this.registrationDateField.setValue(registrationDate);
		this.engineField.setValue(engine);
		this.cubicCapacityField.setValue(cubicCapacity);
		this.transmissionField.setValue(transmission);
		this.horsePowerField.setValue(horsePower);
	}
	
	private HorizontalLayout initHeader() {
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.addComponent(titleField);
		headerLayout.addComponent(discountedPriceField);
		headerLayout.setComponentAlignment(discountedPriceField, Alignment.MIDDLE_RIGHT);
		headerLayout.setWidth(100, Unit.PERCENTAGE);
		headerLayout.setSpacing(false);
		headerLayout.setMargin(false);
		return headerLayout;
	}
	
	private HorizontalLayout initPropertiesLayout() {
		final HorizontalLayout propertiesLayout = new HorizontalLayout();
		final FormLayout propertiesFormLayout1 = new FormLayout();
		final FormLayout propertiesFormLayout2 = new FormLayout();
		propertiesFormLayout1.addStyleName("small-layout");
		propertiesFormLayout2.addStyleName("small-layout");
		propertiesFormLayout1.addComponent(stateField);
		propertiesFormLayout2.addComponent(registrationDateField);
		propertiesFormLayout1.addComponent(engineField);
		propertiesFormLayout2.addComponent(cubicCapacityField);
		propertiesFormLayout1.addComponent(transmissionField);
		propertiesFormLayout2.addComponent(horsePowerField);
		propertiesLayout.addComponent(propertiesFormLayout1);
		propertiesLayout.addComponent(propertiesFormLayout2);
		propertiesLayout.setWidth(100, Unit.PERCENTAGE);
		propertiesFormLayout1.setSpacing(false);
		propertiesFormLayout2.setSpacing(false);
		propertiesFormLayout1.setMargin(false);
		propertiesFormLayout2.setMargin(false);
		propertiesLayout.setMargin(true);
		return propertiesLayout;
	}
	
	private Image initImageField() {
		final Image imageField = new Image();
		imageField.setWidth(200, Unit.PIXELS);
		imageField.setHeight(200, Unit.PIXELS);
		return imageField;
	}
	
	private Label initTitleLabel() {
		final Label label = new Label();
		label.addStyleName(ValoTheme.LABEL_H3);
		label.setWidthUndefined();
		return label;
	}
	
	private Label initSubtitleLabel() {
		final Label label = new Label();
		label.setWidthUndefined();
		return label;
	}
	
	private Label initPropertyLabel() {
		final Label label = new Label();
		label.addStyleName(ValoTheme.LABEL_SMALL);
		return label;
	}

	@Override
	public void localize() {
		this.stateField.setCaption(Localizer.getLocalizedString("state"));
		this.registrationDateField.setCaption(Localizer.getLocalizedString("registration_date"));
		this.engineField.setCaption(Localizer.getLocalizedString("engine"));
		this.cubicCapacityField.setCaption(Localizer.getLocalizedString("cubic_capacity"));
		this.transmissionField.setCaption(Localizer.getLocalizedString("transmission"));
		this.horsePowerField.setCaption(Localizer.getLocalizedString("horse_power"));
	}
}
