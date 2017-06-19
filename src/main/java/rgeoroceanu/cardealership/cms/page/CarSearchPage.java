package rgeoroceanu.cardealership.cms.page;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Notification;

import rgeoroceanu.cardealership.cms.layout.CarSearchLayout;
import rgeoroceanu.cardealership.model.business.Car;
import rgeoroceanu.cardealership.model.cms.CarSearchCriteria;
import rgeoroceanu.cardealership.model.cms.CarSearchResult;

/**
 * Page that provides search funtionality for {@link Car} entries.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
@UIScope
@SpringView
public class CarSearchPage extends Page {

	private static final long serialVersionUID = 1L;
	private final CarSearchLayout searchLayout;

	public CarSearchPage() {
		super();
		searchLayout = new CarSearchLayout();
		searchLayout.addSearchButtonListener( e -> startSearch());
		this.setLayout(searchLayout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) { 
		super.enter(event);
		searchLayout.clear();
	}

	private void startSearch() {
		final CarSearchCriteria search = searchLayout.getSearchCriteria();
		if (search == null) {
			Notification.show("Invalid search options!");
			return;
		}
		final List<Car> results = dataService.getAllCarsBySearchCriteria(search);

		searchLayout.addResults(results.stream().map(car -> {
			final CarSearchResult result = new CarSearchResult(car);
			result.setImageUrls(getImageUrls(car.getId()));
			return result;
		}).collect(Collectors.toList()));
	}
	
	private List<String> getImageUrls(final Long carId) {
		return imageService.getPreviewImageUrls(carId);
	}
}
