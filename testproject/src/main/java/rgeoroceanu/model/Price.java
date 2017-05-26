package rgeoroceanu.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.Currency;

@Getter
@Setter
@Embeddable
public class Price implements Serializable {
	
	private static final long serialVersionUID = 7154303520716874029L;
	@Basic
	private int originalPrice;
	@Basic
	private int discountedPrice;
	@Basic
	private Currency currency = Currency.EURO;
}
