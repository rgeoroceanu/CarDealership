package rgeoroceanu.model.business;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Price {
	private int originalPrice;
	private int discountedPrice;
}
