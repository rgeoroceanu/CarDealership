package rgeoroceanu.cms.layout;

import java.util.List;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

import rgeoroceanu.cms.component.search.CarSearchBox;
import rgeoroceanu.cms.component.search.CarSearchContainer;
import rgeoroceanu.model.CarSearchCriteria;
import rgeoroceanu.model.CarSearchResult;

public class CarSearchLayout extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	private final CarSearchContainer searchContainer;
	private final CarSearchBox searchBox;
	
	public CarSearchLayout() {
		searchContainer = new CarSearchContainer();
		searchBox = new CarSearchBox();
		this.addComponent(searchBox);
		this.addComponent(searchContainer);
		this.setSpacing(true);
		this.setMargin(true);
	}
	
	public CarSearchCriteria getSearchCriteria() throws InvalidValueException {
		return searchBox.getSearchCriteria();
	}
	
	public void addResults(final List<CarSearchResult> results) {
		searchContainer.addItems(results);
	}
	
	public void clear() {
		searchContainer.clear();
	}
	
	public void addSearchButtonListener(ClickListener listener) {
		searchBox.addSearchButtonListener(listener);
	}
}
