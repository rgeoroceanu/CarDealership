package rgeoroceanu.cms.component.search;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cms.component.image.ImagePreview;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;

/**
 * Small box containing information about a {@link Car} item.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class CarOverview extends CustomComponent implements Localizable {
	
	private static final long serialVersionUID = 1L;
	private final ImagePreview imageField;
	private final Label titleField;
	private final Label subtitleField;
	private final Label priceField;
	private final Label stateField;
	private final Label registrationDateField;
	private final Label engineField;
	private final Label cubicCapacityField;
	private final Label transmissionField;
	private final Label horsePowerField;
	private final Button editButton;
	
	public interface EditListener {
		public void edit();
	}
	
	public CarOverview() {
		imageField = initImageField();
		titleField = initTitleLabel();
		subtitleField = initSubtitleLabel();
		priceField = initTitleLabel();
		stateField = initPropertyLabel();
		registrationDateField = initPropertyLabel();
		engineField = initPropertyLabel();
		cubicCapacityField = initPropertyLabel();
		transmissionField = initPropertyLabel();
		horsePowerField = initPropertyLabel();
		editButton = initEditButton();
		final HorizontalLayout headerLayout = initHeader();
		final VerticalLayout infoLayout = new VerticalLayout();
		final HorizontalLayout propertiesLayout = initPropertiesLayout();
		infoLayout.addComponent(headerLayout);
		infoLayout.addComponent(subtitleField);
		infoLayout.addComponent(propertiesLayout);
		infoLayout.addComponent(editButton);
		infoLayout.setSpacing(false);
		infoLayout.setMargin(false);
		infoLayout.setComponentAlignment(editButton, Alignment.BOTTOM_RIGHT);
		final HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(imageField);
		layout.addComponent(infoLayout);
		layout.setExpandRatio(infoLayout, 1);
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setSpacing(true);
		layout.setMargin(true);
		final Panel panel = new Panel();
		panel.setWidth(700, Unit.PIXELS);
		panel.setContent(layout);
		this.setCompositionRoot(panel);
	}
	
	/**
	 * Add click listener for the edit button.
	 * @param editListener click listener
	 */
	public void addEditListener(final ClickListener editListener) {
		editButton.addClickListener(editListener);
	}
	
	/**
	 * Set title of the overview box.
	 * @param title
	 */
	public void setTitle(final String title) {
		this.titleField.setValue(title);
	}
	
	/**
	 * Set subtitle information.
	 * @param subtitle 
	 */
	public void setSubtitle(final String subtitle) {
		this.subtitleField.setValue(subtitle);
	}
	
	/**
	 * Set price data to be displayed.
	 * @param price
	 */
	public void setPrice(final String price) {
		this.priceField.setValue(price);
	}
	
	/**
	 * Set other additional details of the car item.
	 * @param state of the vehicle
	 * @param registrationDate of the vehicle
	 * @param engine type of the vehicle
	 * @param cubicCapacity of the vehicle
	 * @param transmission type
	 * @param horsePower value
	 */
	public void setDetails(final String state, final String registrationDate, 
			final String engine, final String cubicCapacity, final String transmission, 
			final String horsePower) {
		
		this.stateField.setValue(state);
		this.registrationDateField.setValue(registrationDate);
		this.engineField.setValue(engine);
		this.cubicCapacityField.setValue(cubicCapacity);
		this.transmissionField.setValue(transmission);
		this.horsePowerField.setValue(horsePower);
	}
	
	/**
	 * Set image urls that can be viewed.
	 * @param imageUrls
	 */
	public void setImageUrls(final List<String> imageUrls) {
		final List<Resource> resources = new ArrayList<>();
		for (final String url : imageUrls) {
			final Resource resource = new ExternalResource(url);
			resources.add(resource);
		}
		imageField.setImageResources(resources);
	}
	
	private HorizontalLayout initHeader() {
		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.addComponent(titleField);
		headerLayout.addComponent(priceField);
		headerLayout.setComponentAlignment(priceField, Alignment.MIDDLE_RIGHT);
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
		return propertiesLayout;
	}
	
	private ImagePreview initImageField() {
		final ImagePreview imageField = new ImagePreview();
		imageField.setWidth(200, Unit.PIXELS);
		imageField.setHeight(200, Unit.PIXELS);
		return imageField;
	}
	
	private Label initTitleLabel() {
		final Label label = new Label();
		label.addStyleName(ValoTheme.LABEL_LARGE);
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

	private Button initEditButton() {
		final Button button = new Button();
		button.addStyleName(ValoTheme.BUTTON_PRIMARY);
		return button;
	}
	
	@Override
	public void localize() {
		this.stateField.setCaption(Localizer.getLocalizedString("state"));
		this.registrationDateField.setCaption(Localizer.getLocalizedString("registration_date"));
		this.engineField.setCaption(Localizer.getLocalizedString("engine"));
		this.cubicCapacityField.setCaption(Localizer.getLocalizedString("cubic_capacity"));
		this.transmissionField.setCaption(Localizer.getLocalizedString("transmission"));
		this.horsePowerField.setCaption(Localizer.getLocalizedString("horse_power"));
		this.editButton.setCaption(Localizer.getLocalizedString("edit"));
	}
}
