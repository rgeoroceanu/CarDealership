package rgeoroceanu.cms.component.search;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
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
	private final BeanFieldGroup<CarSearchCriteria> binder;
	
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
	public CarSearchCriteria getSearchCriteria() throws InvalidValueException {
		final CarSearchCriteria criteria = getCurrentSearchOptions();
		return criteria;
	}
	
	private BeanFieldGroup<CarSearchCriteria> initBinder() {
		BeanFieldGroup<CarSearchCriteria> binder = new BeanFieldGroup<>(CarSearchCriteria.class);
		binder.bindMemberFields(searchForm);
		binder.setItemDataSource(new CarSearchCriteria());
		return binder;
	}
	
	private CarSearchCriteria getCurrentSearchOptions() throws InvalidValueException {
		if (binder.isValid() == false) {
			throw new InvalidValueException("Invalid search options!");
		}
		try {
			binder.commit();
		} catch (CommitException e) {
			throw new InvalidValueException("Invalid search options!");
		}
		final CarSearchCriteria options = binder.getItemDataSource().getBean();
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
