package rgeoroceanu.cms.page;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

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
	
	public void setLayout(Component layout) {
		this.setCompositionRoot(layout);
	}
}