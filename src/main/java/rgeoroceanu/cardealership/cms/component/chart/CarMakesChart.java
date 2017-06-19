package rgeoroceanu.cardealership.cms.component.chart;

import java.util.Map;
import java.util.Map.Entry;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;

/**
 * Pie chart that displays per car make current availability.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class CarMakesChart extends Chart {

	private static final long serialVersionUID = 1L;
	
	public CarMakesChart() {
		super(ChartType.PIE);
		initConfiguration();
	}	
	
	/**
	 * Set data to be displayed in the chart.
	 * @param carDistributionMap key represents y axis and value represents x axis.
	 */
	public void setDistributionData(final Map<String, Integer> carDistributionMap) {
		final DataSeries series = new DataSeries();
		for (Entry<String, Integer> e : carDistributionMap.entrySet()) {
			final String make = e.getKey();
			final int number = e.getValue();
			final DataSeriesItem item = new DataSeriesItem(make, number);
			series.add(item);
		}
		if(series.size() != 0) {
			series.get(0).setSelected(true);
			series.get(0).setSliced(true);
		}
		Configuration conf = this.getConfiguration();
		conf.setSeries(series);
		this.drawChart(conf);
	}
	
	private void initConfiguration() {
		final PlotOptionsPie plotOptions = new PlotOptionsPie();
		plotOptions.setCursor(Cursor.POINTER);
		DataLabels dataLabels = new DataLabels();
		dataLabels.setEnabled(false);
		Configuration conf = this.getConfiguration();
		conf.setPlotOptions(plotOptions);
		conf.setTitle("");
	}
}