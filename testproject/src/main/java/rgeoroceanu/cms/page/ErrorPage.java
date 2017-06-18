package rgeoroceanu.cms.page;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Error view of the cms.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
@UIScope
public class ErrorPage extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_ERROR_MESSAGE = "Oops! An error occured! You may have navigated to an invalid page!";
	private final Label label;
	
	public ErrorPage() {
		label = new Label();
		this.addComponent(label);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		label.setValue(DEFAULT_ERROR_MESSAGE);
	}
	
	public void setErrorMessage(final String message) {
		label.setValue(message);
	}
}
