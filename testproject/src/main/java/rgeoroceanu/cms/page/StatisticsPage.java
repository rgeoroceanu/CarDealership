package rgeoroceanu.cms.page;

import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import rgeoroceanu.cms.component.chart.CarMakesChart;
import rgeoroceanu.cms.component.chart.SalesChart;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.type.Make;

@Component
public class StatisticsPage extends Page {
	
	private static final long serialVersionUID = 1L;
	private final CarMakesChart makesChart;
	private final SalesChart salesChart;
	final Panel makesPanel;
	final Panel salesPanel;
	
	public StatisticsPage() {
		makesChart = initCarMakesChart();
		salesChart = initSalesChart();
		makesPanel = new Panel();
		salesPanel = new Panel();
		final Layout layout = initMainLayout();
		this.setContent(layout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		setStatisticsData();
	}
	
	@Override
	public void localize() {
		super.localize();
		makesPanel.setCaption(Localizer.getLocalizedString("car_makes"));
		salesPanel.setCaption(Localizer.getLocalizedString("car_sales"));
	}
	
	private void setStatisticsData() {
		final Map<String, Integer> carMakesData = new HashMap<>();
		for (final Entry<Make, Integer> e : dataService.getCarMakesCount().entrySet()) {
			carMakesData.put(e.getKey().toString(), e.getValue());
		}
		makesChart.setDistributionData(carMakesData);
		final LinkedHashMap<String, Integer> salesData = new LinkedHashMap<>();
		String[] months = new DateFormatSymbols().getMonths();
	    for (int i = 0; i < 12; i++) {
	      final String month = months[i];
	      final Integer sales = i * 2;
	      salesData.put(month, sales);
	     
	    }
	    salesChart.setSalesData(salesData);
	}
	
	private Layout initMainLayout() {
		final VerticalLayout layout = new VerticalLayout();
		makesPanel.setContent(makesChart);
		salesPanel.setContent(salesChart);
		layout.addComponent(makesPanel);
		layout.addComponent(salesPanel);
		layout.setSizeFull();
		makesPanel.setSizeFull();
		salesPanel.setSizeFull();
		return layout;
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
}
