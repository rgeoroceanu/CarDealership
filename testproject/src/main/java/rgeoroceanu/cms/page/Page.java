package rgeoroceanu.cms.page;

import java.util.Arrays;
import java.util.Locale;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;

public abstract class Page extends VerticalLayout implements Localizable, View {
	private static final long serialVersionUID = 1L;

	private static final Locale ENGLISH_LOCALE = new Locale("en");
	private static final Locale GERMAN_LOCALE = new Locale("de");
	private ComboBox languageSelect;
	private Button logoutButton;
	private Button addVehicleButton;
	private Button statisticsButton;
	private Panel contentPanel;
	private Button contactButton;
	private Button helpButton;
	private Button aboutButton;

	public Page() {
		this.addStyleName(ValoTheme.UI_WITH_MENU);
		initLanguageSelect();
		initLogoutButton();
		initAddVehicleButton();
		initStatisticsButton();
		initContactButton();
		initHelpButton();
		initAboutButton();
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
	
	public void setContent(final Component content) {
		contentPanel.setContent(content);
	}
	
	private void initLanguageSelect() {
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
		languageSelect.setWidth(7, Unit.EM);
	}
	
	private void initLogoutButton() {
		logoutButton = new Button();
		logoutButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
	}
	
	private void initAddVehicleButton() {
		addVehicleButton = new Button();
		addVehicleButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addVehicleButton.setHeight(70, Unit.PIXELS);
		addVehicleButton.addClickListener(e -> App.getCurrent().navigateToCarPage());
	}
	
	private void initStatisticsButton() {
		statisticsButton = new Button();
		statisticsButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		statisticsButton.setHeight(70, Unit.PIXELS);
		statisticsButton.addClickListener(e -> App.getCurrent().navigateToStartPage());
	}
	
	private void initContactButton() {
		contactButton = new Button();
		contactButton.addStyleName(ValoTheme.BUTTON_LINK);
	}
	
	private void initHelpButton() {
		helpButton = new Button();
		helpButton.addStyleName(ValoTheme.BUTTON_LINK);
	}
	
	private void initAboutButton() {
		aboutButton = new Button();
		aboutButton.addStyleName(ValoTheme.BUTTON_LINK);
	}
	
	private void changeLocale(Locale locale) {
		Localizer.setLocale(locale);
		App app = App.getCurrent();
		if (app != null) {
			app.localize();
		}
	}
}