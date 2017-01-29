package rgeoroceanu.model.business;

import java.time.YearMonth;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.Color;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Feature;
import rgeoroceanu.model.type.Make;
import rgeoroceanu.model.type.State;
import rgeoroceanu.model.type.Transmission;

@Getter
@Setter
public abstract class Vehicle {
	
	private Long id;
	private Make marque;
	private String model;
	private YearMonth registrationDate;
	private int kilometers;
	private Engine engine;
	private int cubicCentimeters;
	private Transmission transmission;
	private Color color;
	private State state;
	private Set<String> lowResImages = new LinkedHashSet<>();
	private Set<String> highResImages = new LinkedHashSet<>();
	private boolean sold;
	private Price price;
	private Set<Feature> feature = new LinkedHashSet<>();
	
}
