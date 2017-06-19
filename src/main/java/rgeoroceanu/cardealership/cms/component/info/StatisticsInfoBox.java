package rgeoroceanu.cms.component.info;

import java.util.Map;

import rgeoroceanu.cms.component.chart.CarMakesChart;

/**
 * {@link InfoBox} that displays a statistics pie chart.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class StatisticsInfoBox extends InfoBox {

	private static final long serialVersionUID = 1L;
	private CarMakesChart chart;
	
	public StatisticsInfoBox() {
		super();
		chart = new CarMakesChart();
		setContent(chart);
	}
	
	/**
	 * Set statistics data to  be displayed.
	 * @param statistics map
	 */
	public void setStatisticsData(final Map<String, Integer> statistics) {
		chart.setDistributionData(statistics);
	}
	
	@Override
	public void localize() {
		super.localize();
		setTitle("Cars per Make");
	}
}
