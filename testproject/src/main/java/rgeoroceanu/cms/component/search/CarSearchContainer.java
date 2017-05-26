package rgeoroceanu.cms.component.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.vaadin.ui.VerticalLayout;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.model.cms.CarSearchResult;

/**
 * Vertical layout that contains multiple {@link CarOverview} components that represent
 * search results..
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class CarSearchContainer extends VerticalLayout implements Localizable {
	
	private static final long serialVersionUID = 1L;
	private final Map<CarOverview, Long> resultsMap = new HashMap<>();
	
	public void addItems(final List<CarSearchResult> items) {
		clear();
		for (final CarSearchResult item : items) {
			final CarOverview overview = buildCarOverview(item);
			overview.addEditListener(e -> App.getCurrent().navigateToCarPage(item.getCarId()));
			this.addComponent(overview);
			resultsMap.put(overview, item.getCarId());
		}
		localize();
		this.setSpacing(true);
	}
	
	/**
	 * Clear all contained {@link CarOverview} items.
	 */
	public void clear() {
		resultsMap.clear();
		this.removeAllComponents();
	}
	
	@Override
	public void localize() {
		resultsMap.keySet().forEach(overview -> overview.localize());	
	}
	
	private CarOverview buildCarOverview(final CarSearchResult result) {
		Preconditions.checkNotNull(result);
		final CarOverview carOverview = new CarOverview();
		carOverview.setTitle(result.getTitle());
		carOverview.setSubtitle(result.getSubtitle());
		carOverview.setDetails(result.getState(), result.getDate(), result.getEngine(), 
				result.getCapacity(), result.getTransmission(), result.getPower());
		carOverview.setPrice(result.getPrice());
		carOverview.setImageUrls(result.getImageUrls());
		return carOverview;
	}
}
