package rgeoroceanu.model.business;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.MotorhomeType;

@Getter
@Setter
public class Motorhome extends Vehicle {
	
	private MotorhomeType type;
	private int length;
	private int weight;
	private int bunks;
	private int axles;
	
}
