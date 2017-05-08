package rgeoroceanu.cms.form;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.type.Feature;

@Component
public class CarForm extends VerticalLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	
	private @PropertyId("type") ComboBox typeField;
	private @PropertyId("marque") ComboBox marqueField;
	private @PropertyId("model") TextField modelField;
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
	private @PropertyId("features") OptionGroup featuresField;
	private Panel generalPanel;
	private Panel detailsPanel;
	private Panel pricingPanel;
	private Panel imagesPanel;
	private Panel featuresPanel;
	
	public CarForm() {
		initTypeField();
		initMarqueField();
		initFeaturesField();
		initModelField();
		
		generalPanel = new Panel();
		detailsPanel = new Panel();
		pricingPanel = new Panel();
		imagesPanel = new Panel();
		featuresPanel = new Panel();
		
		GridLayout generalLayout = new GridLayout(2, 2);
		GridLayout detailsLayout = new GridLayout(2, 2);
		FormLayout pricingLayout = new FormLayout();
		FormLayout featuresLayout = new FormLayout();
		
		generalLayout.addComponent(typeField, 0, 0);
		generalLayout.addComponent(marqueField, 0, 1);
		generalLayout.addComponent(modelField, 1, 1);
		featuresLayout.addComponent(featuresField);
		
		generalLayout.setSpacing(true);
		generalLayout.setMargin(true);
		generalLayout.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
		
		generalPanel.setContent(generalLayout);
		featuresPanel.setContent(featuresLayout);
		detailsPanel.setContent(detailsLayout);
		pricingPanel.setContent(pricingLayout);
		
		this.addComponent(generalPanel);
		this.addComponent(detailsPanel);
		this.addComponent(featuresPanel);
		this.addComponent(imagesPanel);
		this.addComponent(pricingPanel);
		
	}
	
	@Override
	public void localize() {
		typeField.setCaption(Localizer.getLocalizedString("type"));
		marqueField.setCaption(Localizer.getLocalizedString("marque"));
		modelField.setCaption(Localizer.getLocalizedString("model"));
		generalPanel.setCaption(Localizer.getLocalizedString("general"));
		detailsPanel.setCaption(Localizer.getLocalizedString("details"));
		featuresPanel.setCaption(Localizer.getLocalizedString("features"));
		pricingPanel.setCaption(Localizer.getLocalizedString("pricing"));
		imagesPanel.setCaption(Localizer.getLocalizedString("images"));
	}
	
	private void initTypeField() {
		typeField = new ComboBox();
	}

	private void initMarqueField() {
		marqueField = new ComboBox();
	}
	
	private void initModelField() {
		modelField = new TextField();
	}
	
	private void initFeaturesField() {
		featuresField = new OptionGroup();
		featuresField.setMultiSelect(true);
		featuresField.addStyleName("multicol");
		featuresField.addItems(Arrays.asList(Feature.values()));
	}
}
