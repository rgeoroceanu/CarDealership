package rgeoroceanu.model.business;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.CarType;

@Getter
@Setter
public class Car extends Vehicle {
	private CarType type;
	private int doors;
	private int seats;
}
