package rgeoroceanu.cardealership.cms.component.chart;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.ZoomType;
import com.vaadin.addon.charts.model.style.SolidColor;

/**
 * Line chart that displays earnings for a certain period of time.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class EarningsChart extends Chart {

	private static final long serialVersionUID = 1L;

	public EarningsChart() {
		super(ChartType.LINE);
		initConfiguration();
		initLegend();
	}	
	
	/**
	 * Set data to be represented by a line chart.
	 * @param earningsMap key represents y axis and value represents x axis.
	 */
	public void setEarningsData(final LinkedHashMap<String, Integer> earningsMap) {
		final DataSeries series = new DataSeries();
		series.setName("Earnings (€)");
		final PlotOptionsColumn plot = new PlotOptionsColumn();
        plot.setPointPadding(0.2);
        plot.setBorderWidth(0);

		for (Entry<String, Integer> e : earningsMap.entrySet()) {
			final String x = e.getKey();
			final int y = e.getValue();
			this.getConfiguration().getxAxis().addCategory(x);
			final DataSeriesItem item = new DataSeriesItem(x, y);
			series.add(item);
		}
		
		final Configuration conf = this.getConfiguration();
		conf.setSeries(series);
		this.drawChart(conf);
	}
	
	private void initLegend() {
		final Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setAlign(HorizontalAlign.LEFT);
        legend.setX(120);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setY(100);
        legend.setFloating(true);
        legend.setBackgroundColor(new SolidColor("#FFFFFF"));
        this.getConfiguration().setLegend(legend);
	}
	
	private void initConfiguration() {
		final Configuration conf = this.getConfiguration();

        conf.getChart().setZoomType(ZoomType.XY);
        conf.setTitle("");
        final XAxis x = new XAxis();
        conf.addxAxis(x);

        final YAxis primary = new YAxis();
        primary.setTitle("Earnings");
        conf.addyAxis(primary);

        Tooltip tooltip = new Tooltip(false);
        conf.setTooltip(tooltip);
	}
}