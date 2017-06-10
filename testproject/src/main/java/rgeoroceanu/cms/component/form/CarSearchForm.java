package rgeoroceanu.cms.component.form;

import java.util.Arrays;

import com.vaadin.annotations.PropertyId;
import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.cms.CarSearchCriteria;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Make;

/**
 * Form associated with a {@link CarSearchCriteria} object.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class CarSearchForm extends GridLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	private final @PropertyId("make") ComboBox<Make> makeField;
	private final @PropertyId("model") TextField modelField;
	private final @PropertyId("startYear") DateField startYearField;
	private final @PropertyId("endYear") DateField endYearField;
	private final @PropertyId("startPrice") ComboBox<Integer> startPriceField;
	private final @PropertyId("endPrice") ComboBox<Integer> endPriceField;
	private final @PropertyId("engine") ComboBox<Engine> engineField;
	private final @PropertyId("startCapacity") ComboBox<Integer> startCapacityField;
	private final @PropertyId("endCapacity") ComboBox<Integer> endCapacityField;
	private final @PropertyId("startPower") ComboBox<Integer> startPowerField;
	private final @PropertyId("endPower") ComboBox<Integer> endPowerField;
	
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
	
	private ComboBox<Make> initMakeField() {
		final ComboBox<Make> makeField = new ComboBox<Make>();
		makeField.setTextInputAllowed(false);
		makeField.setItems(Arrays.asList(Make.values()));
		makeField.setWidth(110, Unit.PIXELS);
		return makeField;
	}
	
	private ComboBox<Integer> initPriceField() {
		final ComboBox<Integer> priceField = new ComboBox<>();
		priceField.setItems(Arrays.asList(500, 1000, 2500, 5000, 10000, 50000));
		priceField.setWidth(110, Unit.PIXELS);
		return priceField;
	}
	
	private ComboBox<Engine> initEngineField() {
		final ComboBox<Engine> engineField = new ComboBox<>();
		engineField.setTextInputAllowed(false);
		engineField.setItems(Arrays.asList(Engine.values()));
		engineField.setWidth(110, Unit.PIXELS);
		return engineField;
	}

	private ComboBox<Integer> initCapacityField() {
		final ComboBox<Integer> capacityField = new ComboBox<>();
		capacityField.setItems(Arrays.asList(500, 1000, 2000, 3000, 4000, 5000));
		capacityField.setWidth(110, Unit.PIXELS);
		return capacityField;
	}
	
	private ComboBox<Integer> initPowerField() {
		final ComboBox<Integer> powerField = new ComboBox<Integer>();
		powerField.setItems(Arrays.asList(50, 75, 100, 150, 200, 300));
		powerField.setWidth(110, Unit.PIXELS);
		return powerField;
	}
	
	private TextField initModelField() {
		final TextField modelField = new TextField();
		modelField.setWidth(110, Unit.PIXELS);
		return modelField;
	}
	
	private DateField initYearField() {
		final DateField yearField = new DateField();
		yearField.setResolution(DateResolution.YEAR);
		yearField.setWidth(110, Unit.PIXELS);
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