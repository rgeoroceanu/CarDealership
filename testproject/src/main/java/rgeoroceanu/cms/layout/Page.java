package rgeoroceanu.cms.layout;

import java.util.Arrays;
import java.util.Locale;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.tools.Localizable;
import rgeoroceanu.cms.tools.Localizer;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Page extends VerticalLayout implements Localizable {
	private static final long serialVersionUID = 1L;

	private static final Locale ENGLISH_LOCALE = new Locale("en");
	private static final Locale GERMAN_LOCALE = new Locale("de");
	private final ComboBox languageSelect;
	private final Button logoutButton;
	private final Button addVehicleButton;
	private final Button statisticsButton;
	private final Panel contentPanel;
	private final Button contactButton;
	private final Button helpButton;
	private final Button aboutButton;

	public Page() {
		this.addStyleName(ValoTheme.UI_WITH_MENU);
		languageSelect = new ComboBox();
		languageSelect.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
		languageSelect.addStyleName(ValoTheme.COMBOBOX_TINY);
		languageSelect.addStyleName(ValoTheme.LINK_SMALL);
		languageSelect.addItems(Arrays.asList(ENGLISH_LOCALE, GERMAN_LOCALE));
		languageSelect.setItemCaption(ENGLISH_LOCALE, "English");
		languageSelect.setItemCaption(GERMAN_LOCALE, "Deutsch");
		languageSelect.setTextInputAllowed(false);
		languageSelect.setNullSelectionAllowed(false);
		languageSelect.addValueChangeListener(e -> changeLocale((Locale) e.getProperty().getValue()));
		languageSelect.setValue(ENGLISH_LOCALE);
		logoutButton = new Button();
		logoutButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addVehicleButton = new Button();
		addVehicleButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		statisticsButton = new Button();
		statisticsButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		contactButton = new Button();
		contactButton.addStyleName(ValoTheme.BUTTON_LINK);
		helpButton = new Button();
		helpButton.addStyleName(ValoTheme.BUTTON_LINK);
		aboutButton = new Button();
		aboutButton.addStyleName(ValoTheme.BUTTON_LINK);
		contentPanel = new Panel();
		HorizontalLayout headerLayout = new HorizontalLayout();
		HorizontalLayout titleLayout = new HorizontalLayout();
		titleLayout.addStyleName("title");
		HorizontalLayout titleButtonsLayout = new HorizontalLayout();
		HorizontalLayout footerLayout = new HorizontalLayout();
		headerLayout.addComponent(languageSelect);
		headerLayout.addComponent(logoutButton);
		titleButtonsLayout.addComponent(statisticsButton);
		titleButtonsLayout.addComponent(addVehicleButton);
		titleLayout.addComponent(titleButtonsLayout);
		footerLayout.addComponent(contactButton);
		footerLayout.addComponent(helpButton);
		footerLayout.addComponent(aboutButton);
		this.addComponent(headerLayout);
		this.addComponent(titleLayout);
		this.addComponent(contentPanel);
		this.addComponent(footerLayout);
		// aligning
		headerLayout.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
		headerLayout.setComponentAlignment(languageSelect, Alignment.MIDDLE_LEFT);
		titleLayout.setComponentAlignment(titleButtonsLayout, Alignment.TOP_RIGHT);
		// sizing
		addVehicleButton.setHeight(70, Unit.PIXELS);
		statisticsButton.setHeight(70, Unit.PIXELS);
		languageSelect.setWidth(7, Unit.EM);
		contentPanel.setHeight(1280, Unit.PIXELS);
		contentPanel.setWidth(100, Unit.PERCENTAGE);
		headerLayout.setWidth(100, Unit.PERCENTAGE);
		headerLayout.setHeight(70, Unit.PIXELS);
		titleLayout.setWidth(100, Unit.PERCENTAGE);
		titleLayout.setHeight(70, Unit.PIXELS);
		footerLayout.setHeight(70, Unit.PIXELS);
		this.setWidth(100, Unit.PERCENTAGE);

	}

	@Override
	public void localize() {
		// localize
		logoutButton.setCaption(Localizer.getLocalizedString("logout"));
		statisticsButton.setCaption(Localizer.getLocalizedString("statistics"));
		addVehicleButton.setCaption(Localizer.getLocalizedString("addVehicle"));
		contactButton.setCaption(Localizer.getLocalizedString("contact"));
		helpButton.setCaption(Localizer.getLocalizedString("help"));
		aboutButton.setCaption(Localizer.getLocalizedString("about"));
	}
	
	private void changeLocale(Locale locale) {
		Localizer.setLocale(locale);
		App.getCurrent().localize();
	}
}
