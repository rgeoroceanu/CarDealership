package rgeoroceanu.cardealership.cms.layout;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.vaadin.annotations.PropertyId;
import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.ui.AbstractColorPicker.PopupStyle;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import lombok.Getter;
import rgeoroceanu.cardealership.cms.component.form.Form;
import rgeoroceanu.cardealership.cms.component.image.ImagesComponent;
import rgeoroceanu.cardealership.cms.localization.Localizable;
import rgeoroceanu.cardealership.cms.localization.Localizer;
import rgeoroceanu.cardealership.model.business.Car;
import rgeoroceanu.cardealership.model.type.CarType;
import rgeoroceanu.cardealership.model.type.Currency;
import rgeoroceanu.cardealership.model.type.Engine;
import rgeoroceanu.cardealership.model.type.Feature;
import rgeoroceanu.cardealership.model.type.Make;
import rgeoroceanu.cardealership.model.type.State;
import rgeoroceanu.cardealership.model.type.Transmission;

/**
 * Layout associated with a {@link Car} entity for creating, editing or deleting it.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
@Getter
public class CarEditLayout extends PageLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	
	private final @PropertyId("carType") ComboBox<CarType> typeField;
	private final @PropertyId("make") ComboBox<Make> marqueField;
	private final @PropertyId("model") TextField modelField;
	private final @PropertyId("doors") RadioButtonGroup<Integer> doorsField;
	private final @PropertyId("seats") ComboBox<Integer> seatsField;
	private final @PropertyId("registrationDate") InlineDateField registrationDateField;
	private final TextField kilometersField;
	private final @PropertyId("engine") ComboBox<Engine> engineField;
	private final TextField cubicCapacityField;
	private final TextField horsePowerField;
	private final @PropertyId("transmission") ComboBox<Transmission> transmissionField;
	private final ColorPicker colorField;
	private final @PropertyId("state") ComboBox<State> stateField;
	private final ImagesComponent imagesComponent;
	private final TextField originalPriceField;
	private final TextField discountedPriceField;
	private final @PropertyId("currency") ComboBox<Currency> currencyField;
	private final @PropertyId("features") CheckBoxGroup<Feature> featuresField;
	private final @PropertyId("shortDescription") TextArea shortDescriptionField;
	private final @PropertyId("description") TextArea descriptionField;
	private final Button soldButton;
	private final Panel detailsPanel;
	private final Panel pricingPanel;
	private final Panel imagesPanel;
	private final Panel featuresPanel;
	private final Panel descriptionPanel;
	private final Form formLayout;
	
	public CarEditLayout() {
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
		soldButton = initSoldButton();
		
		formLayout = new Form();
		detailsPanel = new Panel();
		pricingPanel = new Panel();
		imagesPanel = new Panel();
		featuresPanel = new Panel();
		descriptionPanel = new Panel();
		
		setupLayout();
		
		formLayout.addComponent(imagesPanel);
		formLayout.addComponent(detailsPanel);
		formLayout.addComponent(featuresPanel);
		formLayout.addComponent(descriptionPanel);
		formLayout.addComponent(pricingPanel);
		formLayout.addActionButton(soldButton);
		formLayout.setSpacing(true);
		this.setContent(formLayout);
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
		kilometersField.setCaption(Localizer.getLocalizedString("kilometers"));
		doorsField.setCaption(Localizer.getLocalizedString("doors"));
		seatsField.setCaption(Localizer.getLocalizedString("seats"));
		colorField.setCaption(Localizer.getLocalizedString("color"));
		originalPriceField.setCaption(Localizer.getLocalizedString("original_price"));
		discountedPriceField.setCaption(Localizer.getLocalizedString("discounted_price"));
		currencyField.setCaption(Localizer.getLocalizedString("currency"));
		shortDescriptionField.setCaption(Localizer.getLocalizedString("short_description"));
		descriptionField.setCaption(Localizer.getLocalizedString("description"));
		descriptionPanel.setCaption(Localizer.getLocalizedString("description"));
		horsePowerField.setCaption(Localizer.getLocalizedString("horse_power"));
		soldButton.setCaption(Localizer.getLocalizedString("sold"));
	}
	
	public void addSaveButtonListener(final ClickListener listener) {
		formLayout.addSaveButtonListener(listener);
	}
	
	public void addRemoveButtonListener(final ClickListener listener) {
		formLayout.addRemoveButtonListener(listener);
	}
	
	public void addDiscardButtonListener(final ClickListener listener) {
		formLayout.addDiscardButtonListener(listener);
	}
	
	public void addSoldButtonListener(final ClickListener listener) {
		soldButton.addClickListener(listener);
	}
	
	public void setActionButtonsEnableState(boolean saveButtonEnabled,
			boolean discardButtonEnabled, boolean removeButtonEnabled,
			boolean soldButtonEnabled) {
		
		formLayout.setActionButtonsEnableState(saveButtonEnabled, discardButtonEnabled, removeButtonEnabled);
		soldButton.setVisible(soldButtonEnabled);
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
		
		
		detailsFormLayout1.addComponent(marqueField);
		detailsFormLayout2.addComponent(modelField);
		detailsFormLayout1.addComponent(typeField);
		detailsFormLayout1.addComponent(stateField);
		
		detailsFormLayout1.addComponent(engineField);
		detailsFormLayout1.addComponent(cubicCapacityField);
		detailsFormLayout2.addComponent(transmissionField);
		detailsFormLayout2.addComponent(horsePowerField);
		detailsFormLayout2.addComponent(kilometersField);
		detailsFormLayout1.addComponent(doorsField);
		detailsFormLayout2.addComponent(seatsField);
		detailsFormLayout1.addComponent(colorField);
		detailsFormLayout2.addComponent(registrationDateField);
		
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
	
	private ComboBox<CarType> initTypeField() {
		final ComboBox<CarType> typeField = new ComboBox<CarType>(null, Arrays.asList(CarType.values()));
		typeField.setTextInputAllowed(false);
		typeField.setEmptySelectionAllowed(false);
		typeField.setRequiredIndicatorVisible(true);
		return typeField;
	}
	
	private ComboBox<Make> initMarqueField() {
		final ComboBox<Make> marqueField = new ComboBox<Make>(null, Arrays.asList(Make.values()));
		marqueField.setTextInputAllowed(false);
		marqueField.setEmptySelectionAllowed(false);
		marqueField.setRequiredIndicatorVisible(true);
		return marqueField;
	}
	
	private TextField initModelField() {
		final TextField modelField = new TextField();
		modelField.setRequiredIndicatorVisible(true);
		return modelField;
		
	}
	
	private CheckBoxGroup<Feature> initFeaturesField() {
		final CheckBoxGroup<Feature> featuresField = new CheckBoxGroup<Feature>();
		featuresField.addStyleName("multicol");
		featuresField.setItems(new ArrayList<>(Feature.forCar()));
		featuresField.setWidth(907, Unit.PIXELS);
		return featuresField;
	}
	
	private ComboBox<Transmission> initTransmissionField() {
		final ComboBox<Transmission> transmissionField = new ComboBox<Transmission>(null, Arrays.asList(Transmission.values()));	
		transmissionField.setTextInputAllowed(false);
		transmissionField.setEmptySelectionAllowed(false);
		transmissionField.setRequiredIndicatorVisible(true);
		return transmissionField;
	}
	
	private RadioButtonGroup<Integer> initDoorsField() {
		final RadioButtonGroup<Integer> doorsField = new RadioButtonGroup<Integer>(null, Arrays.asList(2, 3, 5));
		return doorsField;
	}
	
	private ComboBox<Integer> initSeatsField() {
		final ComboBox<Integer> seatsField = new ComboBox<Integer>(null, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		seatsField.setTextInputAllowed(false);
		seatsField.setEmptySelectionAllowed(false);
		return seatsField;
	}
	
	private InlineDateField initRegistrationDateField() {
		final InlineDateField registrationDateField = new InlineDateField();
		registrationDateField.setResolution(DateResolution.MONTH);
		registrationDateField.setRequiredIndicatorVisible(true);
		return registrationDateField;
	}
	
	private TextField initKilometersField() {
		final TextField kilometersField = new TextField();
		return kilometersField;
	}
	
	private ComboBox<Engine> initEngineField() {
		final ComboBox<Engine> engineField = new ComboBox<Engine>(null, Arrays.asList(Engine.values()));
		engineField.setTextInputAllowed(false);
		engineField.setEmptySelectionAllowed(false);
		engineField.setRequiredIndicatorVisible(true);
		return engineField;
	}
	
	private TextField initCapacityField() {
		final TextField capacityField = new TextField();
		return capacityField;
	}
	
	private TextField initPowerField() {
		final TextField powerField = new TextField();
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
	
	private ComboBox<State> initStateField() {
		final ComboBox<State> stateField = new ComboBox<State>(null, Arrays.asList(State.values()));
		stateField.setTextInputAllowed(false);
		stateField.setEmptySelectionAllowed(false);
		stateField.setRequiredIndicatorVisible(true);
		return stateField;
	}
	
	private TextField initPriceField() {
		final TextField priceField = new TextField();
		priceField.setRequiredIndicatorVisible(true);
		return priceField;
	}
	
	private ComboBox<Currency> initCurrencyField() {
		final ComboBox<Currency> currencyField = new ComboBox<Currency>(null, Arrays.asList(Currency.values()));
		currencyField.setTextInputAllowed(false);
		currencyField.setEmptySelectionAllowed(false);
		currencyField.setRequiredIndicatorVisible(true);
		return currencyField;
	}
	
	private TextArea initDescriptionField() {
		final TextArea descriptionField = new TextArea();
		descriptionField.setRows(6);
		descriptionField.setWidth(600, Unit.PIXELS);
		return descriptionField;
	}
	
	private Button initSoldButton() {
		final Button soldButton = new Button();
		soldButton.addStyleName(ValoTheme.BUTTON_LINK);
		return soldButton;
	}
}
