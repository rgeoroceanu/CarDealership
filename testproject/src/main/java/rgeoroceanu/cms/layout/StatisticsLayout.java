package rgeoroceanu.cms.layout;

import java.util.LinkedHashMap;
import java.util.Map;

import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import rgeoroceanu.cms.component.chart.CarMakesChart;
import rgeoroceanu.cms.component.chart.EarningsChart;
import rgeoroceanu.cms.component.chart.SalesChart;
import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;

public class StatisticsLayout extends VerticalLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	private final CarMakesChart makesChart;
	private final SalesChart salesChart;
	private final EarningsChart earningsChart;
	final Panel makesPanel;
	final Panel salesPanel;
	final Panel earningsPanel;
	
	public StatisticsLayout() {
		makesChart = initCarMakesChart();
		salesChart = initSalesChart();
		earningsChart = initEarningsChart();
		makesPanel = new Panel();
		salesPanel = new Panel();
		earningsPanel = new Panel();
		initMainLayout();
	}
	
	@Override
	public void localize() {
		makesPanel.setCaption(Localizer.getLocalizedString("car_makes"));
		salesPanel.setCaption(Localizer.getLocalizedString("car_sales"));
		earningsPanel.setCaption(Localizer.getLocalizedString("car_earnings"));
	}
	
	public void setStatisticsData(final Map<String, Integer> carMakesData, final LinkedHashMap<String, Integer> salesData,
			LinkedHashMap<String, Integer> earningsData) {
		
		makesChart.setDistributionData(carMakesData);
	    salesChart.setSalesData(salesData);
	    earningsChart.setSalesData(earningsData);
	}
	
	private void initMainLayout() {
		makesPanel.setContent(makesChart);
		salesPanel.setContent(salesChart);
		earningsPanel.setContent(earningsChart);
		this.addComponent(makesPanel);
		this.addComponent(salesPanel);
		this.addComponent(earningsPanel);
		this.setSizeFull();
		makesPanel.setSizeFull();
		salesPanel.setSizeFull();
		earningsPanel.setSizeFull();
	}
	
	private CarMakesChart initCarMakesChart() {
		final CarMakesChart chart = new CarMakesChart();
		chart.setWidth(90, Unit.PERCENTAGE);
		chart.setHeight(300, Unit.PIXELS);
		return chart;
	}
	
	private SalesChart initSalesChart() {
		final SalesChart chart = new SalesChart();
		chart.setWidth(90, Unit.PERCENTAGE);
		chart.setHeight(300, Unit.PIXELS);
		return chart;
	}
	
	private EarningsChart initEarningsChart() {
		final EarningsChart chart = new EarningsChart();
		chart.setWidth(90, Unit.PERCENTAGE);
		chart.setHeight(300, Unit.PIXELS);
		return chart;
	}
	
}
