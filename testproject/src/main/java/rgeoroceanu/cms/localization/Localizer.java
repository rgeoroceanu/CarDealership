package rgeoroceanu.cms.localization;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import com.google.common.base.Preconditions;

public final class Localizer {
	
	private static final Localizer instance = new Localizer();
	@Autowired
	private MessageSource messageSource;
	private Locale locale;
	
	private Localizer() {}
	
	public static Localizer getInstance() {
		return instance;
	}
	
	public static String getLocalizedString(String id) {
		Preconditions.checkNotNull(id);
		Preconditions.checkNotNull(instance.locale);
		String localizedName = id;
		try {
			localizedName = instance.messageSource.getMessage(id, null, instance.locale);
		} catch(NoSuchMessageException e) {
			// skip
		}
		return localizedName;
	}
	
	public static void setLocale(Locale locale) {
		instance.locale = locale;
	}
}
