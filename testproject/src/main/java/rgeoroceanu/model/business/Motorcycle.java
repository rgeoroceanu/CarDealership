package rgeoroceanu.model.business;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.DriveMode;
import rgeoroceanu.model.type.MotorcycleType;

@Getter
@Setter
public class Motorcycle extends Vehicle {
	private MotorcycleType type;
	private DriveMode driveMode;
}
