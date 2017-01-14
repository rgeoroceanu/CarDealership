package rgeoroceanu.cms;

import rgeoroceanu.cms.layout.Page;
import rgeoroceanu.cms.tools.Localizable;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

@SpringUI
@Theme("cms")
public class App extends UI implements Localizable {
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(new Page());
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