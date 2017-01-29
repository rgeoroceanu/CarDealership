package rgeoroceanu.model.business;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.TruckType;

@Getter
@Setter
public class Truck extends Vehicle {

	private TruckType type;
	private int weight;
	private int wheels;
}
