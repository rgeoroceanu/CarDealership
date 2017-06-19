package rgeoroceanu.cardealership.model.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Feature {
	ABS, 
	AIRBAGS, 
	CD, 
	NAVIGATION,
	BORD_COMPUTER, 
	ESP, 
	ELECTRIC_WINDOWS,
	CENTRAL_LOCKING,
	SERVO,
	BLUETOOTH,
	RADIO,
	MANUAL_AC,
	AUTOMATIC_AC,
	LEATHER_INTERIOR,
	PARKING_SENSORS,
	XENON,
	ASR,
	KICK_STARTER,
	ELECTRIC_STARTER,
	CATALYTIC_CONVERTER,
	WINDSHIELD,
	WC,
	SEPARATE_SHOWER,
	BED,
	AUXILIARY_HEATING,
	TV;
	
	public static Set<Feature> forCar() {
		return new HashSet<>(Arrays.asList(ABS, AIRBAGS, CD, BORD_COMPUTER, 
				ESP, ELECTRIC_WINDOWS,CENTRAL_LOCKING,SERVO,BLUETOOTH,RADIO,
				MANUAL_AC,AUTOMATIC_AC,LEATHER_INTERIOR,PARKING_SENSORS,XENON,ASR, NAVIGATION));
	}
	
	public static Set<Feature> forMotorcycle() {
		return new HashSet<>(Arrays.asList(ABS, BORD_COMPUTER, BLUETOOTH, RADIO, NAVIGATION,
				KICK_STARTER, ELECTRIC_STARTER, CATALYTIC_CONVERTER, WINDSHIELD));
	}
	
	public static Set<Feature> forMotorhome() {
		return new HashSet<>(Arrays.asList(ABS, AIRBAGS, CD, BORD_COMPUTER, 
				ELECTRIC_WINDOWS,CENTRAL_LOCKING,BLUETOOTH, RADIO,
				MANUAL_AC, AUTOMATIC_AC, NAVIGATION, WC, SEPARATE_SHOWER, 
				BED, AUXILIARY_HEATING, TV));
	}
	
	public static Set<Feature> forTruck() {
		return new HashSet<>(Arrays.asList(ABS, BORD_COMPUTER, 
				RADIO, NAVIGATION,WC, BED, AUXILIARY_HEATING));
	}
}
