package rgeoroceanu.cms.page;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;

import rgeoroceanu.cms.component.search.CarSearchBox;
import rgeoroceanu.cms.component.search.CarSearchBox.SearchListener;
import rgeoroceanu.cms.component.search.CarSearchContainer;
import rgeoroceanu.model.Car;
import rgeoroceanu.model.CarSearchCriteria;
import rgeoroceanu.model.CarSearchResult;

@Component
public class CarSearchPage extends Page {
	
	private static final long serialVersionUID = 1L;
	private final CarSearchContainer searchContainer;
	private final CarSearchBox searchBox;
	
	public CarSearchPage() {
		super();
		searchContainer = new CarSearchContainer();
		searchBox = new CarSearchBox();
		searchBox.setSearchListener(searchListener);
		final HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(searchBox);
		layout.addComponent(searchContainer);
		layout.setSpacing(true);
		layout.setMargin(true);
		this.setContent(layout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) { 
		searchContainer.clear();
	}

	private final SearchListener searchListener = new SearchListener() {

		@Override
		public void startSearch(CarSearchCriteria searchCriteria) {
			final List<Car> results = dataService.getAllCarsBySearchCriteria(searchCriteria);
			searchContainer.addItems(results.stream().map(car -> {
				final CarSearchResult result = new CarSearchResult(car);
				result.setImageUrls(imageService.getPreviewImageUrls(car.getId()));
				return result;
			}).collect(Collectors.toList()));
		}
	};
}
