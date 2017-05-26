package rgeoroceanu.cms.converter;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import com.vaadin.data.util.converter.Converter;

/**
 * Vaadin data {@link Converter} that converts Date to the year representation.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class DateToYearConverter implements Converter<Date, Integer> {

	private static final long serialVersionUID = 1L;

	@Override
	public Integer convertToModel(Date value, Class<? extends Integer> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		
		if (value == null) {
			return null;
		}
		return new DateTime(value).getYear();
	}

	@Override
	public Date convertToPresentation(Integer value, Class<? extends Date> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		
		if (value == null || value == 0) {
			return null;
		}
		return new DateTime(value, 1, 1, 1, 1).toDate();
	}

	@Override
	public Class<Integer> getModelType() {
		return Integer.class;
	}

	@Override
	public Class<Date> getPresentationType() {
		return Date.class;
	}

}
