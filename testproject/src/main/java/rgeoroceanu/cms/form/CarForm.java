package rgeoroceanu.cms.form;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;

public class CarForm extends FormLayout {

	private static final long serialVersionUID = 1L;
	
	private @PropertyId("type") ComboBox typeField;
	private @PropertyId("doors") ComboBox doorsField;
	private @PropertyId("seats") ComboBox seatsField;


}
