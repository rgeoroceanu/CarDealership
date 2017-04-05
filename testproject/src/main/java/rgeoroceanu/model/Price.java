package rgeoroceanu.model;

import java.util.Currency;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Price {
	private int originalPrice;
	private int discountedPrice;
	private Currency currency;
}
