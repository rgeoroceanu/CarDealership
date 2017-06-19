package rgeoroceanu.cardealership.cms.layout;

import java.util.List;

import com.vaadin.ui.Button.ClickListener;

import rgeoroceanu.cardealership.cms.component.search.CarSearchBox;
import rgeoroceanu.cardealership.cms.component.search.CarSearchContainer;
import rgeoroceanu.cardealership.model.cms.CarSearchCriteria;
import rgeoroceanu.cardealership.model.cms.CarSearchResult;

import com.vaadin.ui.HorizontalLayout;

/**
 * Layout that contains a search box on the left and upon a search, filled on the
 * right side with the appropriate results.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class CarSearchLayout extends PageLayout {

	private static final long serialVersionUID = 1L;
	private final CarSearchContainer searchContainer;
	private final CarSearchBox searchBox;
	
	public CarSearchLayout() {
		final HorizontalLayout layout = new HorizontalLayout();
		searchContainer = new CarSearchContainer();
		searchBox = new CarSearchBox();
		layout.addComponent(searchBox);
		layout.addComponent(searchContainer);
		layout.setSpacing(true);
		layout.setMargin(true);
		this.setContent(layout);
	}
	
	/**
	 * Retrieve the current entered search information.
	 * @return current search data, or null in case the search is not valid.
	 */
	public CarSearchCriteria getSearchCriteria() {
		return searchBox.getSearchCriteria();
	}
	
	/**
	 * Add results to the right side results container.
	 * @param results to be displayed.
	 */
	public void addResults(final List<CarSearchResult> results) {
		searchContainer.addItems(results);
	}
	
	/**
	 * Clears the results container.
	 */
	public void clear() {
		searchContainer.clear();
	}
	
	/**
	 * Add listener for the search button.
	 * @param listener for click events.
	 */
	public void addSearchButtonListener(ClickListener listener) {
		searchBox.addSearchButtonListener(listener);
	}
}
