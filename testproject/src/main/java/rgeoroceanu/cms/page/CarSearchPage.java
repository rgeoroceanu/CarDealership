package rgeoroceanu.cms.page;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;

import rgeoroceanu.cms.component.search.CarSearchBox;
import rgeoroceanu.cms.component.search.CarSearchBox.SearchListener;
import rgeoroceanu.cms.component.search.CarSearchContainer;
import rgeoroceanu.model.Car;
import rgeoroceanu.model.CarSearchCriteria;

@Component
public class CarSearchPage extends Page implements SearchListener {
	
	private static final long serialVersionUID = 1L;
	private final CarSearchContainer searchContainer;
	private final CarSearchBox searchBox;
	
	public CarSearchPage() {
		super();
		searchContainer = new CarSearchContainer();
		searchBox = new CarSearchBox();
		searchBox.setSearchListener(this);
		final HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(searchBox);
		layout.addComponent(searchContainer);
		this.setContent(layout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) { 
		searchContainer.clear();
	}

	@Override
	public void startSearch(CarSearchCriteria searchCriteria) throws InvalidValueException {
		final List<Car> results = dataService.getAllCarsBySearchCriteria(searchCriteria);
		searchContainer.addItems(results);
	}
}
