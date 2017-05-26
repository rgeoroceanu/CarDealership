package rgeoroceanu.model.cms;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.Engine;
import rgeoroceanu.model.type.Make;

@Getter
@Setter
public class CarSearchCriteria {
	
	private Make make;
	private String model; 
	private Integer startYear; 
	private Integer endYear;
	private Integer startPrice; 
	private Integer endPrice;
	private Engine engine;
	private Integer startCapacity;
	private Integer endCapacity;
	private Integer startPower;
	private Integer endPower;
}
