package rgeoroceanu.cms.page;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.layout.PageLayout;
import rgeoroceanu.service.DataService;
import rgeoroceanu.service.ImageService;

/**
 * Abstract class that acts as the presenter of individual view pages. Connects Vaadin layout and components code
 * to the business logic that controls them. Implements {@link View} so that it can be used by {@link Navigator}.
 * All pages that extend this class will have the same display layout.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public abstract class Page extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	@Autowired
	protected DataService dataService;
	@Autowired
	protected ImageService imageService;
	
	public void setLayout(PageLayout layout) {
		this.setCompositionRoot(layout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		handleAccess();
	}
	
	private void handleAccess() {	
		if (App.getCurrent().isUser() == false && App.getCurrent().isAdmin() == false) {
			App.getCurrent().navigateToErrorPage("Permission denied!");
			return;
		}
		if(!App.getCurrent().isAdmin()) {
			final PageLayout layout = (PageLayout) this.getCompositionRoot();
			layout.disableManagerCommands();
		}
	}
}