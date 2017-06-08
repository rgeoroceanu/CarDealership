package rgeoroceanu.model.business;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.CarType;
import rgeoroceanu.model.type.Color;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Feature;
import rgeoroceanu.model.type.Make;
import rgeoroceanu.model.type.State;
import rgeoroceanu.model.type.Transmission;

@Getter
@Setter
@Entity
@Table(name="cars" )
public class Car extends Base implements Serializable {
	
	private static final long serialVersionUID = 5967703253865043398L;
	@Column
	@Enumerated(EnumType.STRING)
	private Make make;
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
	@Basic
	private int horsePower;
	@Column
	@Enumerated(EnumType.STRING)
	private Transmission transmission;
	@Column
	@Enumerated(EnumType.STRING)
	private Color color;
	@Column
	@Enumerated(EnumType.STRING)
	private State state;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> previewImages = new LinkedHashSet<>();
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> fullImages = new LinkedHashSet<>();
	@Basic
	private boolean sold;
	@Embedded
	private Price price = new Price();
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Feature> features = new LinkedHashSet<>();
	@Column
	@Enumerated(EnumType.STRING)
	private CarType carType;
	@Basic
	private int doors;
	@Basic
	private int seats;
	@Basic
	private String shortDescription;
	@Basic
	private String description;
	
	public void addPreviewImage(final String filename) {
		previewImages.add(filename);
	}
	
	public void addFullImage(final String filename) {
		fullImages.add(filename);
	}
	
	public boolean removePreviewImage(final String filename) {
		return previewImages.remove(filename);
	}
	
	public boolean removeFullImage(final String filename) {
		return fullImages.remove(filename);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("super", super.toString()).add("serialVersionUID", serialVersionUID)
				.add("make", make).add("model", model).add("registrationYear", registrationYear)
				.add("registrationMonth", registrationMonth).add("kilometers", kilometers).add("engine", engine)
				.add("cubicCentimeters", cubicCentimeters).add("horsePower", horsePower)
				.add("transmission", transmission).add("color", color).add("state", state)
				.add("previewImages", previewImages).add("fullImages", fullImages).add("sold", sold).add("price", price)
				.add("features", features).add("carType", carType).add("doors", doors).add("seats", seats)
				.add("shortDescription", shortDescription).add("description", description).toString();
	}
	
	
}
