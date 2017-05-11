package rgeoroceanu.model;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.Currency;

@Getter
@Setter
@Embeddable
public class Price {
	@Basic
	private int originalPrice;
	@Basic
	private int discountedPrice;
	@Basic
	private Currency currency;
}
