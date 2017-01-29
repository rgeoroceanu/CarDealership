package rgeoroceanu.model.business;

import java.util.Currency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {
	private int originalPrice;
	private int discountedPrice;
	private Currency currency;
}
