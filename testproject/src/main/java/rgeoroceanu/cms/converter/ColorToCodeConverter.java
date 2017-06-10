package rgeoroceanu.cms.converter;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.shared.ui.colorpicker.Color;

/**
 * Vaadin data {@link Converter} that converts {@link Color} to the code representation.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class ColorToCodeConverter implements Converter<Color, String> {

	private static final long serialVersionUID = 1L;

	@Override
	public Result<String> convertToModel(Color value, ValueContext context) {
		if (value == null) {
			return null;
		}
		return Result.ok(value.getCSS());
	}

	@Override
	public Color convertToPresentation(String value, ValueContext context) {
		if (value == null) {
			return Color.BLACK;
		}
		return new Color(
				Integer.valueOf( value.substring( 1, 3 ), 16 ),
				Integer.valueOf( value.substring( 3, 5 ), 16 ),
				Integer.valueOf( value.substring( 5, 7 ), 16 ) );
	}
}
