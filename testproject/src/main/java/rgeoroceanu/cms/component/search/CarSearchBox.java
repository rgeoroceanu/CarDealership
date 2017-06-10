package rgeoroceanu.cms.component.search;

import com.vaadin.data.Binder;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cms.component.form.CarSearchForm;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.cms.CarSearchCriteria;

/**
 * Search box that contains multiple search terms and a search button to submit the search.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class CarSearchBox extends CustomComponent implements Localizable {
	
	private static final long serialVersionUID = 1L;
	private final CarSearchForm searchForm;
	private final Button searchButton;
	private final Binder<CarSearchCriteria> binder;
	
	public CarSearchBox() {
		searchButton = initSearchButton();
		searchForm = new CarSearchForm();
		binder = initBinder();
		final Layout layout = initMainLayout();
		final Panel wrapPanel = new Panel();
		wrapPanel.setContent(layout);
		this.setCompositionRoot(wrapPanel);
	}
	
	/**
	 * Add listener for the search button click.
	 * @param listener
	 */
	public void addSearchButtonListener(ClickListener listener) {
		searchButton.addClickListener(listener);
	}
	
	/**
	 * Retrieve the current entered search terms.
	 * @return search terms as {@link CarSearchCriteria} object.
	 * @throws InvalidValueException
	 */
	public CarSearchCriteria getSearchCriteria() {
		final CarSearchCriteria criteria = getCurrentSearchOptions();
		return criteria;
	}
	
	public boolean isValidSearch() {
		return binder.isValid();
	}
	
	private Binder<CarSearchCriteria> initBinder() {
		Binder<CarSearchCriteria> binder = new Binder<>(CarSearchCriteria.class);
		binder.bindInstanceFields(searchForm);
		binder.setBean(new CarSearchCriteria());
		return binder;
	}
	
	private CarSearchCriteria getCurrentSearchOptions() {
		if (binder.isValid() == false) {
			return null;
		}
		binder.writeBeanIfValid(binder.getBean());
		final CarSearchCriteria options = binder.getBean();
		return options;
	}
	
	private Layout initMainLayout() {
		final VerticalLayout layout = new VerticalLayout();
		layout.addComponent(searchForm);
		layout.addComponent(searchButton);
		layout.setComponentAlignment(searchButton, Alignment.BOTTOM_CENTER);
		layout.setMargin(true);
		layout.setSpacing(true);
		return layout;
	}
	
	private Button initSearchButton() {
		final Button searchButton = new Button();
		searchButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		searchButton.setWidth(200, Unit.PIXELS);
		return searchButton;
	}
	
	@Override
	public void localize() {
		searchButton.setCaption(Localizer.getLocalizedString("search"));
	}
}
