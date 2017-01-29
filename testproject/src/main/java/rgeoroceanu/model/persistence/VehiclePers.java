package rgeoroceanu.model.persistence;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.Color;
import rgeoroceanu.model.type.Currency;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Feature;
import rgeoroceanu.model.type.Make;
import rgeoroceanu.model.type.State;
import rgeoroceanu.model.type.Transmission;

@Getter
@Setter
@Entity
@Table(name="vehicles")
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "type" )
public abstract class VehiclePers extends BasePers {
	
	@Column
	@Enumerated(EnumType.STRING)
	private Make marque;
	@Basic
	private String model;
	@Basic
	private int registrationYear;
	@Basic
	private int registrationMonth;
	@Basic
	private int kilometers;
	@Column
	@Enumerated(EnumType.STRING)
	private Engine engine;
	@Basic
	private int cubicCentimeters;
	@Column
	@Enumerated(EnumType.STRING)
	private Transmission transmission;
	@Column
	@Enumerated(EnumType.STRING)
	private Color color;
	@Column
	@Enumerated(EnumType.STRING)
	private State state;
	@Lob
	private Set<String> lowResImages = new LinkedHashSet<>();
	@Lob
	private Set<String> highResImages = new LinkedHashSet<>();
	@Basic
	private boolean sold;
	@Basic
	private int originalPrice;
	@Basic
	private int discountedPrice;
	@Column
	@Enumerated(EnumType.STRING)
	private Currency currency;
	@Lob
	private Set<Feature> feature = new LinkedHashSet<>();
	
}
