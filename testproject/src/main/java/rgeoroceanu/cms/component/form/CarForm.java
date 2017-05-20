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

import rgeoroceanu.cms.component.image.ImagesComponent;
import rgeoroceanu.cms.converter.DateToYearConverter;
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
	private final @PropertyId("registrationYear") DateField registrationDateField;
	private final @PropertyId("kilometers") TextField kilometersField;
	private final @PropertyId("engine") ComboBox engineField;
	private final @PropertyId("cubicCentimeters") TextField cubicCapacityField;
	private final @PropertyId("horsePower") TextField horsePowerField;
	private final @PropertyId("transmission") ComboBox transmissionField;
	private final @PropertyId("color") ColorPicker colorField;
	private final @PropertyId("state") ComboBox stateField;
	private final ImagesComponent imagesComponent;
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
		imagesComponent = initImagesComponent();
		typeField = initTypeField();
		marqueField = initMarqueField();
		modelField = initModelField();
		featuresField = initFeaturesField();
		transmissionField = initTransmissionField();
		doorsField = initDoorsField();
		seatsField = initSeatsField();
		registrationDateField = initRegistrationDateField();
		kilometersField = initKilometersField();
		engineField = initEngineField();
		cubicCapacityField = initCapacityField();
		horsePowerField = initPowerField();
		colorField = initColorField();
		stateField = initStateField();
		originalPriceField = initPriceField();
		discountedPriceField = initPriceField();
		currencyField = initCurrencyField();
		descriptionField = initDescriptionField();
		shortDescriptionField = initDescriptionField();
		shortDescriptionField.setRows(2);
		
		detailsPanel = new Panel();
		pricingPanel = new Panel();
		imagesPanel = new Panel();
		featuresPanel = new Panel();
		descriptionPanel = new Panel();
		
		setupLayout();
		
		this.addComponent(imagesPanel);
		this.addComponent(detailsPanel);
		this.addComponent(featuresPanel);
		this.addComponent(pricingPanel);
		this.addComponent(descriptionPanel);
		this.setSpacing(true);
	}
	
	public ImagesComponent getImagesComponent() {
		return imagesComponent;
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
	
	private void setupLayout() {
		final HorizontalLayout detailsLayout = new HorizontalLayout();
		final FormLayout detailsFormLayout1 = new FormLayout();
		final FormLayout detailsFormLayout2 = new FormLayout();
		final HorizontalLayout pricingLayout = new HorizontalLayout();
		final FormLayout pricingFormLayout1 = new FormLayout();
		final FormLayout pricingFormLayout2 = new FormLayout();
		final FormLayout featuresLayout = new FormLayout();
		final FormLayout descriptionLayout = new FormLayout();
		
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
		descriptionLayout.setSpacing(true);
		descriptionLayout.setMargin(true);
		featuresPanel.setContent(featuresLayout);
		detailsPanel.setContent(detailsLayout);
		pricingPanel.setContent(pricingLayout);
		descriptionPanel.setContent(descriptionLayout);
		imagesPanel.setContent(imagesComponent);
	}
	
	private ImagesComponent initImagesComponent() {
		final ImagesComponent imagesComponent = new ImagesComponent();
		return imagesComponent;
	}
	
	private ComboBox initTypeField() {
		final ComboBox typeField = new ComboBox(null, Arrays.asList(CarType.values()));
		typeField.setTextInputAllowed(false);
		typeField.setNullSelectionAllowed(false);
		typeField.setRequired(true);
		return typeField;
	}
	
	private ComboBox initMarqueField() {
		final ComboBox marqueField = new ComboBox(null, Arrays.asList(Make.values()));
		marqueField.setTextInputAllowed(false);
		marqueField.setNullSelectionAllowed(false);
		marqueField.setRequired(true);
		return marqueField;
	}
	
	private TextField initModelField() {
		final TextField modelField = new TextField();
		modelField.setRequired(true);
		modelField.setNullRepresentation("");
		return modelField;
		
	}
	
	private OptionGroup initFeaturesField() {
		final OptionGroup featuresField = new OptionGroup();
		featuresField.setMultiSelect(true);
		featuresField.addStyleName("multicol");
		featuresField.addItems(Arrays.asList(Feature.values()));
		featuresField.setWidth(907, Unit.PIXELS);
		return featuresField;
	}
	
	private ComboBox initTransmissionField() {
		final ComboBox transmissionField = new ComboBox(null, Arrays.asList(Transmission.values()));	
		transmissionField.setTextInputAllowed(false);
		transmissionField.setNullSelectionAllowed(false);
		transmissionField.setRequired(true);
		return transmissionField;
	}
	
	private OptionGroup initDoorsField() {
		final OptionGroup doorsField = new OptionGroup(null, Arrays.asList(2, 3, 5));
		doorsField.setRequired(true);
		return doorsField;
	}
	
	private ComboBox initSeatsField() {
		final ComboBox seatsField = new ComboBox(null, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		seatsField.setTextInputAllowed(false);
		seatsField.setNullSelectionAllowed(false);
		seatsField.setRequired(true);
		return seatsField;
	}
	
	private DateField initRegistrationDateField() {
		final DateField registrationDateField = new InlineDateField();
		registrationDateField.setResolution(Resolution.YEAR);
		registrationDateField.setRequired(true);
		registrationDateField.setConverter(new DateToYearConverter());
		return registrationDateField;
	}
	
	private TextField initKilometersField() {
		final TextField kilometersField = new TextField();
		kilometersField.addValidator(new IntegerRangeValidator("", 0, 1000000));
		return kilometersField;
	}
	
	private ComboBox initEngineField() {
		final ComboBox engineField = new ComboBox(null, Arrays.asList(Engine.values()));
		engineField.setTextInputAllowed(false);
		engineField.setNullSelectionAllowed(false);
		engineField.setRequired(true);
		return engineField;
	}
	
	private TextField initCapacityField() {
		final TextField capacityField = new TextField();
		capacityField.addValidator(new IntegerRangeValidator("", 299, 15000));
		capacityField.setRequired(true);
		return capacityField;
	}
	
	private TextField initPowerField() {
		final TextField powerField = new TextField();
		powerField.addValidator(new IntegerRangeValidator("", 0, 1000));
		powerField.setRequired(true);
		return powerField;
	}
	
	private ColorPicker initColorField() {
		final ColorPicker colorField = new ColorPicker();
		colorField.setHSVVisibility(false);
		colorField.setRGBVisibility(false);
		colorField.setTextfieldVisibility(false);
		colorField.setPopupStyle(PopupStyle.POPUP_SIMPLE);
		return colorField;
	}
	
	private ComboBox initStateField() {
		final ComboBox stateField = new ComboBox(null, Arrays.asList(State.values()));
		stateField.setTextInputAllowed(false);
		stateField.setNullSelectionAllowed(false);
		stateField.setRequired(true);
		return stateField;
	}
	
	private TextField initPriceField() {
		final TextField priceField = new TextField();
		priceField.setRequired(true);
		priceField.addValidator(new IntegerRangeValidator("", 0, 100000));
		priceField.setNullRepresentation("");
		return priceField;
	}
	
	private ComboBox initCurrencyField() {
		final ComboBox currencyField = new ComboBox(null, Arrays.asList(Currency.values()));
		currencyField.setTextInputAllowed(false);
		currencyField.setNullSelectionAllowed(false);
		currencyField.setRequired(true);
		return currencyField;
	}
	
	private TextArea initDescriptionField() {
		final TextArea descriptionField = new TextArea();
		descriptionField.setRows(6);
		descriptionField.setWidth(600, Unit.PIXELS);
		descriptionField.setNullRepresentation("");
		return descriptionField;
	}
}
