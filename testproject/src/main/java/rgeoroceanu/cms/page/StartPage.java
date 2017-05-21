package rgeoroceanu.cms.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.component.info.ImageInfoBox;
import rgeoroceanu.cms.component.info.StatisticsInfoBox;
import rgeoroceanu.model.Car;
import rgeoroceanu.model.type.Make;

@Component
public class StartPage extends Page {
	
	private static final long serialVersionUID = 1L;
	private final StatisticsInfoBox statisticsBox;
	private final ImageInfoBox latestCarBox;
	
	public StartPage() {
		final GridLayout layout = initLayout();
		statisticsBox = initStatisticsBox();
		latestCarBox = initLatestCarBox();
		layout.addComponent(statisticsBox, 0, 0);
		layout.addComponent(latestCarBox, 1, 0);
		this.setContent(layout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		setCarBoxData();
		setStatisticsData();
	}
	
	private ImageInfoBox initLatestCarBox() {
		final ImageInfoBox infoBox = new ImageInfoBox();
		infoBox.addMoreButtonListener(e -> App.getCurrent().navigateToSearchPage());
		return infoBox;
	}
	
	
	private StatisticsInfoBox initStatisticsBox() {
		final StatisticsInfoBox statisticsBox = new StatisticsInfoBox();
		statisticsBox.addMoreButtonListener(e -> App.getCurrent().navigateToStatisticsPage());
		return statisticsBox;
	}
	
	private void setStatisticsData() {
		final Map<String, Integer> data = new HashMap<>();
		for (final Entry<Make, Integer> e : dataService.getCarMakesCount().entrySet()) {
			data.put(e.getKey().toString(), e.getValue());
		}
		statisticsBox.setStatisticsData(data);
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
		if (latest.getPreviewImages().isEmpty() == false) {
			final String filename = latest.getPreviewImages().iterator().next();
			imageUrl = imageService.getImageUrl(filename, latest.getId());
		}
		String title = "";
		if (latest != null) {
			title = latest.getMake() + " " + latest.getModel();
		}
		latestCarBox.setTitle(title);
		latestCarBox.setImage(imageUrl);
	}
	
	private GridLayout initLayout() {
		final GridLayout layout = new GridLayout(3, 2);
		layout.setSpacing(true);
		layout.setMargin(true);
		return layout;
	}
}
