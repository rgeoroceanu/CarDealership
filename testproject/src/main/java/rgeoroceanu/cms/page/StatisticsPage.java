package rgeoroceanu.cms.page;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import rgeoroceanu.cms.layout.StatisticsLayout;
import rgeoroceanu.cms.localization.Localizer;
import rgeoroceanu.model.type.Make;

/**
 * Page that displays certain statistics data.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
public class StatisticsPage extends Page {
	
	private static final long serialVersionUID = 1L;
	private final StatisticsLayout statisticsLayout;
	
	public StatisticsPage() {
		statisticsLayout = new StatisticsLayout();
		this.setLayout(statisticsLayout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		setStatisticsData();
	}
	
	private void setStatisticsData() {
		final Map<String, Integer> carMakesData = new HashMap<>();
		for (final Entry<Make, Integer> e : dataService.getCarMakesCount().entrySet()) {
			carMakesData.put(e.getKey().toString(), e.getValue());
		}
		
		final LocalDateTime currentDate = LocalDateTime.now();
		final LinkedHashMap<String, Integer> salesData = new LinkedHashMap<>();
		final String[] months = new DateFormatSymbols(Localizer.getCurrentLocale()).getMonths();
		for (final Entry<Integer, Integer> e : dataService.getMonthlyPurchasesCount(currentDate.minusMonths(12), currentDate).entrySet()) {
			final String month = months[e.getKey() - 1];
			salesData.put(month, e.getValue());
		}
	    
		final LinkedHashMap<String, Integer> earningsData = new LinkedHashMap<>();
		for (final Entry<Integer, Integer> e : dataService.getMonthlyEarnings(currentDate.minusMonths(12), currentDate).entrySet()) {
			final String month = months[e.getKey() - 1];
			earningsData.put(month, e.getValue());
		}
	  
		statisticsLayout.setStatisticsData(carMakesData, salesData, earningsData);
	}
}
