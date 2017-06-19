package rgeoroceanu.cardealership.cms;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

import rgeoroceanu.cardealership.cms.localization.Localizable;
import rgeoroceanu.cardealership.cms.page.CarEditPage;
import rgeoroceanu.cardealership.cms.page.CarSearchPage;
import rgeoroceanu.cardealership.cms.page.DealershipEditPage;
import rgeoroceanu.cardealership.cms.page.ErrorPage;
import rgeoroceanu.cardealership.cms.page.StartPage;
import rgeoroceanu.cardealership.cms.page.StatisticsPage;
import rgeoroceanu.cardealership.cms.page.UserEditPage;
import rgeoroceanu.cardealership.cms.page.UsersPage;
import rgeoroceanu.cardealership.model.type.Role;

/**
 * UI entry point of the CMS.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@SpringUI(path = "cms")
@Theme("cms")
@Widgetset("rgeoroceanu.cms.widgetset.AppWidgetSet")
public class App extends UI implements Localizable {
	private static final long serialVersionUID = 1L;
	/**
	 * View identifier of the start page.
	 */
	private static final String START_PAGE_NAV_NAME = "start";
	/**
	 * View identifier of the car edit page.
	 */
	private static final String CAR_EDIT_PAGE_NAV_NAME = "car";
	/**
	 * View identifier of the car search page.
	 */
	private static final String CAR_SEARCH_PAGE_NAV_NAME = "car_search";
	/**
	 * View identifier of the statistics page.
	 */
	private static final String STATISTICS_PAGE_NAV_NAME = "statistics";
	/**
	 * View identifier of the error page.
	 */
	private static final String ERROR_PAGE_NAV_NAME = "error";
	/**
	 * View identifier of the users page.
	 */
	private static final String USERS_PAGE_NAV_NAME = "users";
	/**
	 * View identifier of the user edit page.
	 */
	private static final String USER_EDIT_PAGE_NAV_NAME = "user";
	/**
	 * View identifier of the user edit page.
	 */
	private static final String DEALERSHIP_EDIT_PAGE_NAV_NAME = "dealership";
	private static final String LOGOUT_URL = "../logout";
	@Autowired
	private StartPage startPage;
	@Autowired
	private CarEditPage carEditPage;
	@Autowired
	private CarSearchPage carSearchPage;
	@Autowired
	private StatisticsPage statisticsPage;
	@Autowired
	private ErrorPage errorPage;
	@Autowired
	private UsersPage usersPage;
	@Autowired
	private DealershipEditPage dealershipEditPage;
	@Autowired
	private UserEditPage userEditPage;
	private Navigator navigator;
	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
		navigator = new Navigator(this, this);
		navigator.addView(START_PAGE_NAV_NAME, startPage);
		navigator.addView(CAR_EDIT_PAGE_NAV_NAME, carEditPage);
		navigator.addView(CAR_SEARCH_PAGE_NAV_NAME, carSearchPage);
		navigator.addView(STATISTICS_PAGE_NAV_NAME, statisticsPage);
		navigator.addView(ERROR_PAGE_NAV_NAME, errorPage);
		navigator.addView(USERS_PAGE_NAV_NAME, usersPage);
		navigator.addView(USER_EDIT_PAGE_NAV_NAME, userEditPage);
		navigator.addView(DEALERSHIP_EDIT_PAGE_NAV_NAME, dealershipEditPage);
		navigator.navigateTo(START_PAGE_NAV_NAME);
		navigator.setErrorView(errorPage);
        localize();
    }
	
	/**
	 * Get the current App UI instance.
	 * @return the app UI instance.
	 */
	public static App getCurrent() {
		return (App) UI.getCurrent();
	}
	
	@Override
	public void localize() {
		localizeRecursive(carEditPage);
		localizeRecursive(carSearchPage);
		localizeRecursive(startPage);
		localizeRecursive(statisticsPage);
		localizeRecursive(usersPage);
		localizeRecursive(userEditPage);
		localizeRecursive(dealershipEditPage);
	}
	
	/**
	 * Navigate to the car editing page and fill the form car data belonging to car id.
	 * @param carId identifier of the car data with which to fill the form data; if null is provided,
	 * the form is empty.
	 */
	public void navigateToCarPage(Long carId) {
		final StringBuilder path = new StringBuilder(CAR_EDIT_PAGE_NAV_NAME);
		if (carId != null) {
			path.append("/");
			path.append(String.valueOf(carId));
		}
		navigator.navigateTo(path.toString());
	}
	
	/**
	 * Navigate to the car searching page.
	 */
	public void navigateToSearchPage() {
		navigator.navigateTo(CAR_SEARCH_PAGE_NAV_NAME);
	}
	
	/**
	 * Navigate to the start searching page.
	 */
	public void navigateToStartPage() {
		navigator.navigateTo(START_PAGE_NAV_NAME);
	}
	
	/**
	 * Navigate to the statistics page.
	 */
	public void navigateToStatisticsPage() {
		navigator.navigateTo(STATISTICS_PAGE_NAV_NAME);
	}
	
	/**
	 * Navigate to error page, optionally changing the error message.
	 * @param message
	 */
	public void navigateToErrorPage(final String message) {
		navigator.navigateTo(ERROR_PAGE_NAV_NAME + "/" + message);
	}
	
	/**
	 * Navigate to the users page.
	 */
	public void navigateToUsersPage() {
		navigator.navigateTo(USERS_PAGE_NAV_NAME);
	}
	
	/**
	 * Navigate to the dealership edit page.
	 */
	public void navigateToDealershipEditPage() {
		navigator.navigateTo(DEALERSHIP_EDIT_PAGE_NAV_NAME);
	}
	
	/**
	 * Navigate to the user editing page and fill the form user data belonging to user id.
	 * @param userId identifier of the user data with which to fill the form data; if null is provided,
	 * the form is empty.
	 */
	public void navigateToUserPage(Long userId) {
		final StringBuilder path = new StringBuilder(USER_EDIT_PAGE_NAV_NAME);
		if (userId != null) {
			path.append("/");
			path.append(String.valueOf(userId));
		}
		navigator.navigateTo(path.toString());
	}
	
	/**
	 * Logout from application.
	 */
	public void logout() {
		Page.getCurrent().open(LOGOUT_URL, null);
	}
	
	/**
	 * Check if current has CMS authority.
	 * @return true if user has CMS role.
	 */
	public boolean isUser() {
		return getAuthorities().contains(Role.CMS.toString());
	}
	
	/**
	 * Check if current user has ADMIN authority.
	 * @return true if user has admin role.
	 */
	public boolean isAdmin() {
		return getAuthorities().contains(Role.ADMIN.toString());
	}
	
	private void localizeRecursive(HasComponents root) {
		if(root instanceof Localizable) {
			((Localizable) root).localize();
		}
		for(Component child : root) {
	        if(child instanceof Localizable) {
	        	Localizable localizable = (Localizable)child;
	        	localizable.localize();
	        	if(child instanceof HasComponents) {
	        		localizeRecursive((HasComponents) child);
	        	}
	        } else if(child instanceof HasComponents) {
	        	localizeRecursive((HasComponents) child);
	        }
	    }
	}
	
	private Set<String> getAuthorities() {
		Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		Set<String> authorities = new HashSet<>();
		if(auth!= null) {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
			if(user != null && user.getAuthorities() != null) {
				for(GrantedAuthority ga : user.getAuthorities()) {
					authorities.add(ga.getAuthority());
				}
			}
		}
		return authorities;
	}
}