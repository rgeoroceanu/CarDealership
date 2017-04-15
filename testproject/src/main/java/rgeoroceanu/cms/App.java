package rgeoroceanu.cms;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

import rgeoroceanu.cms.layout.CarPage;
import rgeoroceanu.cms.layout.StartPage;
import rgeoroceanu.cms.localization.Localizable;

@SpringUI
@Theme("cms")
public class App extends UI implements Localizable {
	private static final long serialVersionUID = 1L;
	private static final String START_PAGE_NAV_NAME = "start";
	private static final String CAR_PAGE_NAV_NAME = "add-car";
	@Autowired
	private StartPage startPage;
	@Autowired
	private CarPage carPage;
	private Navigator navigator;
	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
		navigator = new Navigator(this, this);
		navigator.addView(START_PAGE_NAV_NAME, startPage);
		navigator.addView(CAR_PAGE_NAV_NAME, carPage);
		navigator.navigateTo(START_PAGE_NAV_NAME);
        localize();
    }

	public static App getCurrent() {
		return (App) UI.getCurrent();
	}
	
	@Override
	public void localize() {
		localizeRecursive(this);
	}
	
	private void localizeRecursive(HasComponents root) {
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