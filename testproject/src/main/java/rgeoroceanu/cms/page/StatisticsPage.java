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
		final LinkedHashMap<String, Integer> earningsData = new LinkedHashMap<>();
		final String[] months = new DateFormatSymbols(Localizer.getCurrentLocale()).getMonths();
		final int currentMonth = currentDate.getMonthValue();
		final int currentYear = currentDate.getYear();
		final Map<Integer, Integer> unorderedSales = dataService.getMonthlyPurchasesCount(currentDate.minusMonths(12), currentDate);
		final Map<Integer, Integer> unorderedEarnings = dataService.getMonthlyEarnings(currentDate.minusMonths(12), currentDate);
		
		for (int i = currentMonth - 11; i <= currentMonth; i++) {
			final int monthValue = i > 0 ? i : 12 + i; 
			final int year = i > 0 ? currentYear : currentYear - 1; 
			Integer salesValue = unorderedSales.get(monthValue);
			Integer earningsValue = unorderedEarnings.get(monthValue);
			final String month = months[monthValue - 1] + " " + year;
			if (salesValue == null) {
				salesValue = 0;
			}
			if (earningsValue == null) {
				earningsValue = 0;
			}
			salesData.put(month, salesValue);
			earningsData.put(month, earningsValue);
		}
	  
		statisticsLayout.setStatisticsData(carMakesData, salesData, earningsData);
	}
}
