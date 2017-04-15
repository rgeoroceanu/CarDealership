package rgeoroceanu.cms.form;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

public class CarForm extends FormLayout {

	private static final long serialVersionUID = 1L;
	
	private @PropertyId("type") ComboBox typeField;
	private @PropertyId("marque") ComboBox marqueField;
	private @PropertyId("model") TextField model;
	private @PropertyId("doors") ComboBox doorsField;
	private @PropertyId("seats") ComboBox seatsField;
	private @PropertyId("registrationYear") ComboBox registrationYearField;
	private @PropertyId("registrationMonth") ComboBox registrationMonthField;
	private @PropertyId("kilometers") TextField kilometersField;
	private @PropertyId("engine") ComboBox engineField;
	private @PropertyId("cubicCentimeters") TextField cubicCapacityField;
	private @PropertyId("transmission") ComboBox transmissionField;
	private @PropertyId("color") ColorPicker colorField;
	private @PropertyId("state") ComboBox stateField;
	// TODO upload component for multiple images
	private @PropertyId("price.originalPrice") TextField originalPriceField;
	private @PropertyId("price.discountedPrice") TextField discountedPriceField;
	private @PropertyId("price.currency") TextField currencyField;
	private @PropertyId("features") ListSelect featuresField;
	
	public CarForm() {
		initTypeField();
		initMarqueField();
	}
	
	private void initTypeField() {
		typeField = new ComboBox();
	}

	private void initMarqueField() {
		marqueField = new ComboBox();
	}
}
