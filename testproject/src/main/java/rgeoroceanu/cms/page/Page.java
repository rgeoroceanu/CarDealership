package rgeoroceanu.cms.page;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

import rgeoroceanu.cms.layout.PageLayout;
import rgeoroceanu.service.DataService;
import rgeoroceanu.service.ImageService;

public abstract class Page extends CustomComponent implements View {
	private static final long serialVersionUID = 1L;
	
	private PageLayout layout;
	@Autowired
	protected DataService dataService;
	@Autowired
	protected ImageService imageService;
	
	public Page() {
		layout = new PageLayout();
		this.setCompositionRoot(layout);
	}
	
	protected void setContent(final Component content) {
		layout.setContent(content);
	}
	
	protected void alignCenterContent() {
		layout.alignCenterContent();
	}
	
	protected void setContentWidth(int width, Unit unit) {
		layout.setContentWidth(width, unit);
	}
	
	protected void setContentBorderless() {
		layout.setContentBorderless();
	}
}