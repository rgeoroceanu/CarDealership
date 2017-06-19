package rgeoroceanu.cardealership.cms.component.info;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cardealership.cms.localization.Localizable;
import rgeoroceanu.cardealership.cms.localization.Localizer;

/**
 * Box with a title, content and more button.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public abstract class InfoBox extends CustomComponent implements Localizable {

	private static final long serialVersionUID = 1L;
	private final String CONTENT_POSITION_CSS = "top: 0px; left: 0px;";
	private final String BUTTON_POSITION_CSS = "bottom: 10px; right: 10px;";
	private final String TITLE_POSITION_CSS = "top: 5px; left: 0px; right: 0px;";
	private final Button moreButton;
	private final AbsoluteLayout contentLayout;
	private final Label titleLabel;
	
	public InfoBox() {
		moreButton = initMoreButton();
		titleLabel = initTitleLabel();
		contentLayout = initContentLayout();
		Panel panel = new Panel();		
		panel.setContent(contentLayout);
		this.setCompositionRoot(panel);
		
	}
	
	/**
	 * Add click listener for the more button.
	 * @param listener
	 */
	public void addMoreButtonListener(ClickListener listener) {
		moreButton.addClickListener(listener);
	}
	
	/** 
	 * Set content of the box.
	 * @param content component
	 */
	protected void setContent(final Component content) {
		contentLayout.removeComponent(moreButton);
		contentLayout.removeComponent(titleLabel);
		contentLayout.addComponent(content, CONTENT_POSITION_CSS);
		contentLayout.addComponent(titleLabel, TITLE_POSITION_CSS);
		contentLayout.addComponent(moreButton, BUTTON_POSITION_CSS);
	}
	
	/**
	 * Set display title
	 * @param title to be displayed on top.
	 */
	public void setTitle(final String title) {
		titleLabel.setValue("<p style='color:white;background-color: rgba(169,169,169, 0.5'>"
				+ title + "</p>");
	}
	
	private AbsoluteLayout initContentLayout() {
		final AbsoluteLayout layout = new AbsoluteLayout();
		layout.addComponent(titleLabel, TITLE_POSITION_CSS);
		layout.addComponent(moreButton, BUTTON_POSITION_CSS);
		layout.setWidth(400, Unit.PIXELS);
		layout.setHeight(400, Unit.PIXELS);
		return layout;
	}
	
	private Button initMoreButton() {
		final Button button = new Button();
		button.addStyleName(ValoTheme.BUTTON_LINK);
		return button;
	}

	private Label initTitleLabel() {
		final Label label = new Label();
		label.setContentMode(ContentMode.HTML);
		label.setWidth(100, Unit.PERCENTAGE);
		return label;
	}
	
	@Override
	public void localize() {
		moreButton.setCaption(Localizer.getLocalizedString("more") + "...");
	}
}
