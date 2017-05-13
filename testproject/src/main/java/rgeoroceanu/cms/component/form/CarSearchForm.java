package rgeoroceanu.cms.component.form;

import java.util.Arrays;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

import rgeoroceanu.cms.converter.DateToYearConverter;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Make;

public class CarSearchForm extends GridLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	private final @PropertyId("make") ComboBox makeField;
	private final @PropertyId("model") TextField modelField;
	private final @PropertyId("startYear") DateField startYearField;
	private final @PropertyId("endYear") DateField endYearField;
	private final @PropertyId("startPrice") ComboBox startPriceField;
	private final @PropertyId("endPrice") ComboBox endPriceField;
	private final @PropertyId("engine") ComboBox engineField;
	private final @PropertyId("startCapacity") ComboBox startCapacityField;
	private final @PropertyId("endCapacity") ComboBox endCapacityField;
	private final @PropertyId("startPower") ComboBox startPowerField;
	private final @PropertyId("endPower") ComboBox endPowerField;
	
	public CarSearchForm() {
		makeField = initMakeField();
		modelField = initModelField();
		startYearField = initYearField();
		endYearField = initYearField();
		startPriceField = initPriceField();
		endPriceField = initPriceField();
		engineField = initEngineField();
		startCapacityField = initCapacityField();
		endCapacityField = initCapacityField();
		startPowerField = initPowerField();
		endPowerField = initPowerField();
		initMainLayout();
	}
	
	private void initMainLayout() {
		this.setColumns(2);
		this.setRows(6);
		this.addComponent(makeField, 0, 0);
		this.addComponent(modelField, 1, 0);
		this.addComponent(startYearField, 0, 1);
		this.addComponent(endYearField, 1, 1);
		this.addComponent(startPriceField, 0, 2);
		this.addComponent(endPriceField, 1, 2);
		this.addComponent(engineField, 0, 3);
		this.addComponent(startCapacityField, 0, 4);
		this.addComponent(endCapacityField, 1, 4);
		this.addComponent(startPowerField, 0, 5);
		this.addComponent(endPowerField, 1, 5);
		this.setSpacing(true);
	}
	
	private ComboBox initMakeField() {
		final ComboBox makeField = new ComboBox();
		makeField.setTextInputAllowed(false);
		makeField.addItems(Arrays.asList(Make.values()));
		makeField.setWidth(110, Unit.PIXELS);
		return makeField;
	}
	
	private ComboBox initPriceField() {
		final ComboBox priceField = new ComboBox();
		priceField.addItems(Arrays.asList(500, 1000, 2500, 5000, 10000, 50000));
		priceField.setWidth(110, Unit.PIXELS);
		return priceField;
	}
	
	private ComboBox initEngineField() {
		final ComboBox engineField = new ComboBox();
		engineField.setTextInputAllowed(false);
		engineField.addItems(Arrays.asList(Engine.values()));
		engineField.setWidth(110, Unit.PIXELS);
		return engineField;
	}

	private ComboBox initCapacityField() {
		final ComboBox capacityField = new ComboBox();
		capacityField.addItems(Arrays.asList(500, 1000, 2000, 3000, 4000, 5000));
		capacityField.setWidth(110, Unit.PIXELS);
		return capacityField;
	}
	
	private ComboBox initPowerField() {
		final ComboBox powerField = new ComboBox();
		powerField.addItems(Arrays.asList(50, 75, 100, 150, 200, 300));
		powerField.setWidth(110, Unit.PIXELS);
		return powerField;
	}
	
	private TextField initModelField() {
		final TextField modelField = new TextField();
		modelField.setNullRepresentation("");
		modelField.setWidth(110, Unit.PIXELS);
		return modelField;
	}
	
	private DateField initYearField() {
		final DateField yearField = new DateField();
		yearField.setResolution(Resolution.YEAR);
		yearField.setWidth(110, Unit.PIXELS);
		yearField.setConverter(new DateToYearConverter());
		return yearField;
	}

	@Override
	public void localize() {
		final String startString = Localizer.getLocalizedString("start");
		final String endString = Localizer.getLocalizedString("end");
		final String priceString = Localizer.getLocalizedString("price");
		final String yearString = Localizer.getLocalizedString("year");
		final String capacityString = Localizer.getLocalizedString("cubic_capacity");
		final String powerString = Localizer.getLocalizedString("horse_power");
		makeField.setCaption(Localizer.getLocalizedString("marque"));
		modelField.setCaption(Localizer.getLocalizedString("model"));
		startYearField.setCaption(startString + " " + yearString);
		endYearField.setCaption(endString + " " + yearString);
		startPriceField.setCaption(startString + " " + priceString);
		endPriceField.setCaption(endString + " " + priceString);
		engineField.setCaption(Localizer.getLocalizedString("engine"));
		startCapacityField.setCaption(startString + " " + capacityString);
		endCapacityField.setCaption(endString + " " + capacityString);
		startPowerField.setCaption(startString + " " + powerString);
		endPowerField.setCaption(endString + " " + powerString);
	}
}
