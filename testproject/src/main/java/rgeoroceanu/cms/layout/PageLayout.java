package rgeoroceanu.cms.layout;

import java.util.Arrays;
import java.util.Locale;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;

/**
 * Layout of all pages of this application. It is composed mainly by header, content and footer.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class PageLayout extends VerticalLayout implements Localizable {
	private static final long serialVersionUID = 1L;
	private static final Locale ENGLISH_LOCALE = new Locale("en");
	private static final Locale GERMAN_LOCALE = new Locale("de");
	
	private final ComboBox languageSelect;
	private final MenuBar menuBar;
	private final Button logoutButton;
	private final MenuItem vehiclesItem;
	private final MenuItem searchItem;
	private final MenuItem addVehicleItem;
	private final MenuItem homeItem;
	private final MenuItem statisticsItem;
	private final MenuItem managerItem;
	private final MenuItem usersItem;
	private final MenuItem generalItem;
	private final Panel contentPanel;
	private final Button contactButton;
	private final Button helpButton;
	private final Button aboutButton;
	
	public PageLayout() {
		this.addStyleName(ValoTheme.UI_WITH_MENU);
		this.languageSelect = initLanguageSelect();
		this.logoutButton = initLogoutButton();
		this.menuBar = new MenuBar();
		this.homeItem = menuBar.addItem("", null, command -> App.getCurrent().navigateToStartPage());
		this.homeItem.setStyleName("button");
		this.menuBar.addStyleName("titlemenubar");
		this.menuBar.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		this.menuBar.setHeight(100, Unit.PERCENTAGE);
		this.vehiclesItem = menuBar.addItem("", null, null);
		this.vehiclesItem.setStyleName("button");
		this.searchItem = this.vehiclesItem.addItem("", command -> App.getCurrent().navigateToSearchPage());
		this.addVehicleItem = this.vehiclesItem.addItem("", command -> App.getCurrent().navigateToCarPage(null));
		this.statisticsItem = menuBar.addItem("", null, command -> App.getCurrent().navigateToStatisticsPage());
		this.statisticsItem.setStyleName("button");
		this.managerItem = menuBar.addItem("", null, null);
		this.managerItem.setStyleName("button");
		this.usersItem = this.managerItem.addItem("", null);
		this.generalItem = this.managerItem.addItem("", null);
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
		titleButtonsLayout.addComponent(menuBar);
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
		statisticsItem.setText(Localizer.getLocalizedString("statistics"));
		vehiclesItem.setText(Localizer.getLocalizedString("vehicles"));
		addVehicleItem.setText(Localizer.getLocalizedString("add"));
		searchItem.setText(Localizer.getLocalizedString("search"));
		contactButton.setCaption(Localizer.getLocalizedString("contact"));
		helpButton.setCaption(Localizer.getLocalizedString("help"));
		aboutButton.setCaption(Localizer.getLocalizedString("about"));
		homeItem.setText(Localizer.getLocalizedString("home"));
		managerItem.setText(Localizer.getLocalizedString("manage"));
		usersItem.setText(Localizer.getLocalizedString("users"));
		generalItem.setText(Localizer.getLocalizedString("general"));
	}
	
	/**
	 * Set the component that is displayed in the content section.
	 * @param content component to be displayed.
	 */
	public void setContent(final Component content) {
		contentPanel.setContent(content);
	}
	
	/**
	 * Align content to the center of the page.
	 */
	public void alignCenterContent() {
		this.setComponentAlignment(contentPanel, Alignment.TOP_CENTER);
	}
	
	/**
	 * Set width of the content section.
	 * @param width value
	 * @param unit for the width.
	 */
	public void setContentWidth(int width, Unit unit) {
		contentPanel.setWidth(width, unit);
	}
	
	/**
	 * Removes border from the content section.
	 */
	public void setContentBorderless() {
		contentPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
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
