package rgeoroceanu.model.persistence;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.DriveMode;
import rgeoroceanu.model.type.MotorcycleType;

@Getter
@Setter
@Entity
@DiscriminatorValue( value="motorcycle" )
public class MotorcyclePers extends VehiclePers {
	
	@Column
	@Enumerated(EnumType.STRING)
	private MotorcycleType motorcycleType;
	@Column
	@Enumerated(EnumType.STRING)
	private DriveMode driveMode;
	
}
