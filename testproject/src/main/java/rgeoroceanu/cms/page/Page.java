package rgeoroceanu.cms.page;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

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
import rgeoroceanu.service.DataService;

public abstract class Page extends VerticalLayout implements Localizable, View {
	private static final long serialVersionUID = 1L;

	private static final Locale ENGLISH_LOCALE = new Locale("en");
	private static final Locale GERMAN_LOCALE = new Locale("de");
	private final ComboBox languageSelect;
	private final Button logoutButton;
	private final Button addVehicleButton;
	private final Button searchButton;
	private final Button statisticsButton;
	private final Panel contentPanel;
	private final Button contactButton;
	private final Button helpButton;
	private final Button aboutButton;
	@Autowired
	protected DataService dataService;
	
	public Page() {
		this.addStyleName(ValoTheme.UI_WITH_MENU);
		this.languageSelect = initLanguageSelect();
		this.logoutButton = initLogoutButton();
		this.addVehicleButton = initAddVehicleButton();
		this.searchButton = initSearchButton();
		this.statisticsButton = initStatisticsButton();
		this.contactButton = initContactButton();
		this.helpButton = initHelpButton();
		this.aboutButton = initAboutButton();
		contentPanel = new Panel();
		
		HorizontalLayout headerLayout = new HorizontalLayout();
		HorizontalLayout titleLayout = new HorizontalLayout();
		titleLayout.addStyleName("title");
		HorizontalLayout titleButtonsLayout = new HorizontalLayout();
		HorizontalLayout footerLayout = new HorizontalLayout();
		headerLayout.addComponent(languageSelect);
		headerLayout.addComponent(logoutButton);
		titleButtonsLayout.addComponent(statisticsButton);
		titleButtonsLayout.addComponent(searchButton);
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
		//contentPanel.setWidth(100, Unit.PERCENTAGE);
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
		searchButton.setCaption(Localizer.getLocalizedString("search"));
		contactButton.setCaption(Localizer.getLocalizedString("contact"));
		helpButton.setCaption(Localizer.getLocalizedString("help"));
		aboutButton.setCaption(Localizer.getLocalizedString("about"));
	}
	
	public void setContent(final Component content) {
		contentPanel.setContent(content);
	}
	
	private ComboBox initLanguageSelect() {
		final ComboBox languageSelect = new ComboBox();
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
		return languageSelect;
	}
	
	private Button initLogoutButton() {
		final Button logoutButton = new Button();
		logoutButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		return logoutButton;
	}
	
	private Button initAddVehicleButton() {
		final Button addVehicleButton = new Button();
		addVehicleButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		addVehicleButton.setHeight(70, Unit.PIXELS);
		addVehicleButton.addClickListener(e -> App.getCurrent().navigateToCarPage());
		return addVehicleButton;
	}
	
	private Button initSearchButton() {
		final Button searchButton = new Button();
		searchButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		searchButton.setHeight(70, Unit.PIXELS);
		searchButton.addClickListener(e -> App.getCurrent().navigateToSearchPage());
		return searchButton;
	}
	
	private Button  initStatisticsButton() {
		Button statisticsButton = new Button();
		statisticsButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		statisticsButton.setHeight(70, Unit.PIXELS);
		statisticsButton.addClickListener(e -> App.getCurrent().navigateToStartPage());
		return statisticsButton;
	}
	
	private Button initContactButton() {
		Button contactButton = new Button();
		contactButton.addStyleName(ValoTheme.BUTTON_LINK);
		return contactButton;
	}
	
	private Button initHelpButton() {
		Button helpButton = new Button();
		helpButton.addStyleName(ValoTheme.BUTTON_LINK);
		return helpButton;
	}
	
	private Button initAboutButton() {
		Button aboutButton = new Button();
		aboutButton.addStyleName(ValoTheme.BUTTON_LINK);
		return aboutButton;
	}
	
	private void changeLocale(Locale locale) {
		Localizer.setLocale(locale);
		App app = App.getCurrent();
		if (app != null) {
			app.localize();
		}
	}
}