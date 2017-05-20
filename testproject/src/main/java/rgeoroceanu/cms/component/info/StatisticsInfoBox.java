package rgeoroceanu.cms.component.info;

import java.util.Map;

import rgeoroceanu.cms.component.chart.CarMakesChart;

public class StatisticsInfoBox extends InfoBox {

	private static final long serialVersionUID = 1L;
	private CarMakesChart chart;
	
	public StatisticsInfoBox() {
		super();
		chart = new CarMakesChart();
		setContent(chart);
	}
	
	public void setStatisticsData(final Map<String, Integer> statistics) {
		chart.setDistributionData(statistics);
	}
	
	@Override
	public void localize() {
		super.localize();
		setTitle("Cars per Make");
	}
}
