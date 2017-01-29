package rgeoroceanu.model.persistence;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.CarType;

@Getter
@Setter
@Entity
@DiscriminatorValue( value="car" )
public class CarPers extends VehiclePers {
	
	@Column
	@Enumerated(EnumType.STRING)
	private CarType carType;
	@Basic
	private int doors;
	@Basic
	private int seats;
}
