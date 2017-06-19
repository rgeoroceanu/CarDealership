package rgeoroceanu.cardealership.model.cms;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.cardealership.model.type.Engine;
import rgeoroceanu.cardealership.model.type.Make;

@Getter
@Setter
public class CarSearchCriteria {
	
	private Make make;
	private String model; 
	private LocalDate startYear; 
	private LocalDate endYear;
	private Integer startPrice; 
	private Integer endPrice;
	private Engine engine;
	private Integer startCapacity;
	private Integer endCapacity;
	private Integer startPower;
	private Integer endPower;
	
	@Override
	public String toString() {
		return "CarSearchCriteria [make=" + make + ", model=" + model + ", startYear=" + startYear + ", endYear="
				+ endYear + ", startPrice=" + startPrice + ", endPrice=" + endPrice + ", engine=" + engine
				+ ", startCapacity=" + startCapacity + ", endCapacity=" + endCapacity + ", startPower=" + startPower
				+ ", endPower=" + endPower + "]";
	}
	
	
}
