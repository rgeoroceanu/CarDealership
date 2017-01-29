package rgeoroceanu.model.persistence;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import rgeoroceanu.model.type.MotorhomeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue( value="motorhome" )
public class MotorhomePers extends VehiclePers {
	
	@Column
	@Enumerated(EnumType.STRING)
	private MotorhomeType motorhomeType;
	@Basic
	private int length;
	@Basic
	private int weight;
	@Basic
	private int bunks;
	@Basic
	private int axles;
	
}
