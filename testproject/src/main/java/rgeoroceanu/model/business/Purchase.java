package rgeoroceanu.model.business;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

@Getter
@Setter
public class Purchase {
	
	private Long id;
	private Vehicle vehicle;
	private DateTime purchaseDate;
	private int price;

}
