package rgeoroceanu.cms.component.form;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractColorPicker.PopupStyle;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.type.CarType;
import rgeoroceanu.model.type.Currency;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Feature;
import rgeoroceanu.model.type.Make;
import rgeoroceanu.model.type.State;
import rgeoroceanu.model.type.Transmission;

@Component
public class CarForm extends Form implements Localizable {

	private static final long serialVersionUID = 1L;
	
	private final @PropertyId("carType") ComboBox typeField;
	private final @PropertyId("make") ComboBox marqueField;
	private final @PropertyId("model") TextField modelField;
	private final @PropertyId("doors") OptionGroup doorsField;
	private final @PropertyId("seats") ComboBox seatsField;
	private final DateField registrationDateField;
	private final @PropertyId("kilometers") TextField kilometersField;
	private final @PropertyId("engine") ComboBox engineField;
	private final @PropertyId("cubicCentimeters") TextField cubicCapacityField;
	private final @PropertyId("horsePower") TextField horsePowerField;
	private final @PropertyId("transmission") ComboBox transmissionField;
	private final @PropertyId("color") ColorPicker colorField;
	private final @PropertyId("state") ComboBox stateField;
	// TODO upload component for multiple images
	private final @PropertyId("price.originalPrice") TextField originalPriceField;
	private final @PropertyId("price.discountedPrice") TextField discountedPriceField;
	private final @PropertyId("price.currency") ComboBox currencyField;
	private final @PropertyId("features") OptionGroup featuresField;
	private final @PropertyId("shortDescription") TextArea shortDescriptionField;
	private final @PropertyId("description") TextArea descriptionField;
	private final Panel detailsPanel;
	private final Panel pricingPanel;
	private final Panel imagesPanel;
	private final Panel featuresPanel;
	private final Panel descriptionPanel;
	
	public CarForm() {
		typeField = new ComboBox(null, Arrays.asList(CarType.values()));
		typeField.setTextInputAllowed(false);
		typeField.setNullSelectionAllowed(false);
		typeField.setRequired(true);
		marqueField = new ComboBox(null, Arrays.asList(Make.values()));
		marqueField.setTextInputAllowed(false);
		marqueField.setNullSelectionAllowed(false);
		marqueField.setRequired(true);
		modelField = new TextField();
		modelField.setRequired(true);
		modelField.setNullRepresentation("");
		featuresField = new OptionGroup();
		featuresField.setMultiSelect(true);
		featuresField.addStyleName("multicol");
		featuresField.addItems(Arrays.asList(Feature.values()));
		featuresField.setWidth(907, Unit.PIXELS);
		transmissionField = new ComboBox(null, Arrays.asList(Transmission.values()));	
		transmissionField.setTextInputAllowed(false);
		transmissionField.setNullSelectionAllowed(false);
		transmissionField.setRequired(true);
		doorsField = new OptionGroup(null, Arrays.asList(2, 3, 5));
		doorsField.setRequired(true);
		seatsField = new ComboBox(null, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		seatsField.setTextInputAllowed(false);
		seatsField.setNullSelectionAllowed(false);
		seatsField.setRequired(true);
		registrationDateField = new InlineDateField();
		registrationDateField.setResolution(Resolution.MONTH);
		registrationDateField.setRequired(true);
		kilometersField = new TextField();
		kilometersField.addValidator(new IntegerRangeValidator("", 0, 1000000));
		engineField = new ComboBox(null, Arrays.asList(Engine.values()));
		engineField.setTextInputAllowed(false);
		engineField.setNullSelectionAllowed(false);
		engineField.setRequired(true);
		cubicCapacityField = new TextField();
		cubicCapacityField.addValidator(new IntegerRangeValidator("", 299, 15000));
		cubicCapacityField.setRequired(true);
		horsePowerField = new TextField();
		horsePowerField.addValidator(new IntegerRangeValidator("", 0, 1000));
		horsePowerField.setRequired(true);
		colorField = new ColorPicker();
		colorField.setHSVVisibility(false);
		colorField.setRGBVisibility(false);
		colorField.setTextfieldVisibility(false);
		colorField.setPopupStyle(PopupStyle.POPUP_SIMPLE);
		stateField = new ComboBox(null, Arrays.asList(State.values()));
		stateField.setTextInputAllowed(false);
		stateField.setNullSelectionAllowed(false);
		stateField.setRequired(true);
		originalPriceField = new TextField();
		originalPriceField.setRequired(true);
		originalPriceField.addValidator(new IntegerRangeValidator("", 0, 100000));
		originalPriceField.setNullRepresentation("");
		discountedPriceField = new TextField();
		discountedPriceField.addValidator(new IntegerRangeValidator("", 0, 100000));
		discountedPriceField.setRequired(true);
		discountedPriceField.setNullRepresentation("");
		currencyField = new ComboBox(null, Arrays.asList(Currency.values()));
		currencyField.setTextInputAllowed(false);
		currencyField.setNullSelectionAllowed(false);
		currencyField.setRequired(true);
		descriptionField = new TextArea();
		descriptionField.setRows(6);
		descriptionField.setWidth(200, Unit.PIXELS);
		descriptionField.setNullRepresentation("");
		shortDescriptionField = new TextArea();
		shortDescriptionField.setRows(2);
		shortDescriptionField.setWidth(200, Unit.PIXELS);
		shortDescriptionField.setNullRepresentation("");
		
		detailsPanel = new Panel();
		pricingPanel = new Panel();
		imagesPanel = new Panel();
		featuresPanel = new Panel();
		descriptionPanel = new Panel();
		
		HorizontalLayout detailsLayout = new HorizontalLayout();
		FormLayout detailsFormLayout1 = new FormLayout();
		FormLayout detailsFormLayout2 = new FormLayout();
		HorizontalLayout pricingLayout = new HorizontalLayout();
		FormLayout pricingFormLayout1 = new FormLayout();
		FormLayout pricingFormLayout2 = new FormLayout();
		FormLayout featuresLayout = new FormLayout();
		FormLayout descriptionLayout = new FormLayout();
		
		detailsFormLayout1.addComponent(typeField);
		detailsFormLayout1.addComponent(marqueField);
		detailsFormLayout2.addComponent(new Label());
		detailsFormLayout2.addComponent(modelField);
		detailsFormLayout2.addComponent(registrationDateField);
		detailsFormLayout1.addComponent(stateField);
		
		detailsFormLayout1.addComponent(engineField);
		detailsFormLayout1.addComponent(cubicCapacityField);
		detailsFormLayout2.addComponent(transmissionField);
		detailsFormLayout2.addComponent(horsePowerField);
		detailsFormLayout1.addComponent(doorsField);
		detailsFormLayout2.addComponent(new Label());
		detailsFormLayout2.addComponent(seatsField);
		detailsFormLayout1.addComponent(colorField);
		
		featuresLayout.addComponent(featuresField);
		
		pricingFormLayout1.addComponent(currencyField);
		pricingFormLayout1.addComponent(originalPriceField);
		pricingFormLayout2.addComponent(new Label());
		pricingFormLayout2.addComponent(discountedPriceField);
		descriptionLayout.addComponent(shortDescriptionField);
		descriptionLayout.addComponent(descriptionField);
		
		detailsLayout.addComponent(detailsFormLayout1);
		detailsLayout.addComponent(detailsFormLayout2);
		pricingLayout.addComponent(pricingFormLayout1);
		pricingLayout.addComponent(pricingFormLayout2);
		
		detailsFormLayout1.setSpacing(true);
		detailsFormLayout1.setMargin(true);
		detailsFormLayout2.setSpacing(true);
		detailsFormLayout2.setMargin(true);
		pricingFormLayout1.setMargin(true);
		pricingFormLayout1.setSpacing(true);
		pricingFormLayout2.setMargin(true);
		pricingFormLayout2.setSpacing(true);
		featuresLayout.setSpacing(true);
		featuresLayout.setMargin(true);
		featuresPanel.setContent(featuresLayout);
		detailsPanel.setContent(detailsLayout);
		pricingPanel.setContent(pricingLayout);
		descriptionPanel.setContent(descriptionLayout);
		
		this.addComponent(detailsPanel);
		this.addComponent(featuresPanel);
		this.addComponent(imagesPanel);
		this.addComponent(pricingPanel);
		this.addComponent(descriptionPanel);
		
	}
	
	@Override
	public void localize() {
		super.localize();
		typeField.setCaption(Localizer.getLocalizedString("type"));
		marqueField.setCaption(Localizer.getLocalizedString("marque"));
		modelField.setCaption(Localizer.getLocalizedString("model"));
		detailsPanel.setCaption(Localizer.getLocalizedString("details"));
		featuresPanel.setCaption(Localizer.getLocalizedString("features"));
		pricingPanel.setCaption(Localizer.getLocalizedString("pricing"));
		imagesPanel.setCaption(Localizer.getLocalizedString("images"));
		registrationDateField.setCaption(Localizer.getLocalizedString("registration_date"));
		stateField.setCaption(Localizer.getLocalizedString("state"));
		engineField.setCaption(Localizer.getLocalizedString("engine"));
		cubicCapacityField.setCaption(Localizer.getLocalizedString("cubic_capacity"));
		transmissionField.setCaption(Localizer.getLocalizedString("transmission"));
		doorsField.setCaption(Localizer.getLocalizedString("doors"));
		seatsField.setCaption(Localizer.getLocalizedString("seats"));
		colorField.setCaption(Localizer.getLocalizedString("color"));
		originalPriceField.setCaption(Localizer.getLocalizedString("original_price"));
		discountedPriceField.setCaption(Localizer.getLocalizedString("discounted_price"));
		currencyField.setCaption(Localizer.getLocalizedString("currency"));
		shortDescriptionField.setCaption(Localizer.getLocalizedString("short_description"));
		descriptionField.setCaption(Localizer.getLocalizedString("description"));
		descriptionPanel.setCaption(Localizer.getLocalizedString("description"));
	}
	
}
