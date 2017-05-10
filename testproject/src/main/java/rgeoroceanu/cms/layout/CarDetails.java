//package rgeoroceanu.cms.layout;
//
//import com.vaadin.data.fieldgroup.PropertyId;
//import com.vaadin.ui.ColorPicker;
//import com.vaadin.ui.FormLayout;
//import com.vaadin.ui.OptionGroup;
//import com.vaadin.ui.TextArea;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.themes.ValoTheme;
//
//public class CarDetails extends FormLayout {
//
//	private static final long serialVersionUID = 1L;
//	private final @PropertyId("carType") TextField typeField;
//	private final @PropertyId("marque") TextField marqueField;
//	private final @PropertyId("model") TextField modelField;
//	private final @PropertyId("doors") TextField doorsField;
//	private final @PropertyId("seats") TextField seatsField;
//	private final @PropertyId("registrationMonth") TextField registrationMonthField;
//	private final @PropertyId("registrationYear") TextField registrationYearField;
//	private final @PropertyId("kilometers") TextField kilometersField;
//	private final @PropertyId("engine") TextField engineField;
//	private final @PropertyId("cubicCentimeters") TextField cubicCapacityField;
//	private final @PropertyId("transmission") TextField transmissionField;
//	private final @PropertyId("color") ColorPicker colorField;
//	private final @PropertyId("state") TextField stateField;
//	// TODO upload component for multiple images
//	private final @PropertyId("price.originalPrice") TextField originalPriceField;
//	private final @PropertyId("price.discountedPrice") TextField discountedPriceField;
//	private final @PropertyId("price.currency") TextField currencyField;
//	private final @PropertyId("features") OptionGroup featuresField;
//	private final @PropertyId("shortDescription") TextField shortDescriptionField;
//	private final @PropertyId("description") TextArea descriptionField;
//	
//	public CarDetails() {
//		marqueField = createTitleField();
//		modelField = createTitleField();
//		shortDescriptionField = createSubtitleField();
//	}
//	
//	private TextField createTitleField() {
//		final TextField textField = new TextField();
//		textField.setReadOnly(true);
//		textField.addStyleName(ValoTheme.LABEL_H1);
//		return textField;
//	}
//	
//	private TextField createSubtitleField() {
//		final TextField textField = new TextField();
//		textField.setReadOnly(true);
//		textField.addStyleName(ValoTheme.LABEL_H2);
//		return textField;
//	}
//	
//	private TextField createInfoField() {
//		final TextField textField = new TextField();
//		textField.setReadOnly(true);
//		textField.addStyleName(ValoTheme.LABEL_H3);
//	}
//}
