package rgeoroceanu.cms.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import rgeoroceanu.cms.layout.StartLayout;
import rgeoroceanu.model.Car;
import rgeoroceanu.model.type.Make;

@Component
public class StartPage extends Page {
	
	private static final long serialVersionUID = 1L;
	private final StartLayout startLayout;
	
	public StartPage() {
		startLayout = new StartLayout();
		this.setContent(startLayout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		setCarBoxData();
		setStatisticsData();
	}
	
	private void setStatisticsData() {
		final Map<String, Integer> data = new HashMap<>();
		for (final Entry<Make, Integer> e : dataService.getCarMakesCount().entrySet()) {
			data.put(e.getKey().toString(), e.getValue());
		}
		startLayout.addStatistics(data);
	}
	
	private void setCarBoxData() {
		final List<Car> latestCars = dataService.getLatestCars(1);
		final Car latest;
		
		if (latestCars.isEmpty() == false) {
			latest = latestCars.get(0);
		} else {
			latest = null;
		}
		
		String imageUrl = null;
		if (latest != null && latest.getPreviewImages().isEmpty() == false) {
			final String filename = latest.getPreviewImages().iterator().next();
			imageUrl = imageService.getImageUrl(filename, latest.getId());
		}
		
		String title = "";
		if (latest != null) {
			title = latest.getMake() + " " + latest.getModel();
		}
		
		startLayout.setCarInfo(title, imageUrl);
	}
}
