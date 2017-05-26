package rgeoroceanu.cms.layout;

import java.util.Map;

import com.vaadin.ui.GridLayout;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.component.info.ImageInfoBox;
import rgeoroceanu.cms.component.info.StatisticsInfoBox;

public class StartLayout extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	private final StatisticsInfoBox statisticsBox;
	private final ImageInfoBox latestCarBox;
	
	public StartLayout() {
		super(3, 2);
		statisticsBox = initStatisticsBox();
		latestCarBox = initLatestCarBox();
		this.addComponent(statisticsBox, 0, 0);
		this.addComponent(latestCarBox, 1, 0);
		this.setSpacing(true);
		this.setMargin(true);
	}
	
	public void addStatistics(final Map<String, Integer> statistics) {
		statisticsBox.setStatisticsData(statistics);
	}
	
	public void setCarInfo(final String title, final String carImageUrl) {
		latestCarBox.setTitle(title);
		latestCarBox.setImage(carImageUrl);
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
}
