package rgeoroceanu.model.persistence;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.TruckType;

@Getter
@Setter
@Entity
@DiscriminatorValue( value="truck" )
public class TruckPers extends VehiclePers {

	@Column
	@Enumerated(EnumType.STRING)
	private TruckType truckType;
	@Basic
	private int weight;
	@Basic
	private int wheels;
}
