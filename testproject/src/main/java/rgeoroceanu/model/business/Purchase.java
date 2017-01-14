package rgeoroceanu.model.business;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.DateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Purchase {
	
	private Long id;
	private Vehicle vehicle;
	private DateTime purchaseDate;
	private int price;

}
