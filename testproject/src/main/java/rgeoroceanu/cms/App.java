package rgeoroceanu.cms;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.page.CarEditPage;
import rgeoroceanu.cms.page.CarSearchPage;
import rgeoroceanu.cms.page.StartPage;

@SpringUI
@Theme("cms")
@Widgetset("rgeoroceanu.cms.widgetset.AppWidgetSet")
public class App extends UI implements Localizable {
	private static final long serialVersionUID = 1L;
	private static final String START_PAGE_NAV_NAME = "start";
	private static final String CAR_EDIT_PAGE_NAV_NAME = "car";
	private static final String CAR_SEARCH_PAGE_NAV_NAME = "car_search";
	@Autowired
	private StartPage startPage;
	@Autowired
	private CarEditPage carEditPage;
	@Autowired
	private CarSearchPage carSearchPage;
	private Navigator navigator;
	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
		navigator = new Navigator(this, this);
		navigator.addView(START_PAGE_NAV_NAME, startPage);
		navigator.addView(CAR_EDIT_PAGE_NAV_NAME, carEditPage);
		navigator.addView(CAR_SEARCH_PAGE_NAV_NAME, carSearchPage);
		navigator.navigateTo(START_PAGE_NAV_NAME);
		// TODO add error page
        localize();
    }
	
	public static App getCurrent() {
		return (App) UI.getCurrent();
	}
	
	@Override
	public void localize() {
		localizeRecursive(carEditPage);
		localizeRecursive(carSearchPage);
		localizeRecursive(startPage);
	}
	
	public void navigateToCarPage(Long carId) {
		final StringBuilder path = new StringBuilder(CAR_EDIT_PAGE_NAV_NAME);
		if (carId != null) {
			path.append("/");
			path.append(String.valueOf(carId));
		}
		navigator.navigateTo(path.toString());
	}
	
	public void navigateToSearchPage() {
		navigator.navigateTo(CAR_SEARCH_PAGE_NAV_NAME);
	}
	
	public void navigateToStartPage() {
		navigator.navigateTo(START_PAGE_NAV_NAME);
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
}