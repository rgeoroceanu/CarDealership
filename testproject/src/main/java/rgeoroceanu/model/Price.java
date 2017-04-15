package rgeoroceanu.model;

import java.util.Currency;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

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
