package rgeoroceanu.model;

import java.util.LinkedHashSet;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

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
public class Car extends Base {
	
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
	@Lob
	private LinkedHashSet<String> previewImages = new LinkedHashSet<>();
	@Lob
	private LinkedHashSet<String> fullImages = new LinkedHashSet<>();
	@Basic
	private boolean sold;
	@Embedded
	private Price price = new Price();
	@Lob
	private LinkedHashSet<Feature> features = new LinkedHashSet<>();
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
}
