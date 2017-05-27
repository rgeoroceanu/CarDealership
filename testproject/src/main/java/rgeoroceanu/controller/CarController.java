package rgeoroceanu.controller;

import org.springframework.stereotype.Controller;

import rgeoroceanu.model.api.CarSearchCriteriaTo;
import rgeoroceanu.model.api.ResponseTo;

/**
 * Controller for retrieving vehicle information.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Controller(value = "api")
public interface CarController {
	
	public ResponseTo retrieveAllCars();
	
	public ResponseTo retrieveCarsBySearchCriteria(final CarSearchCriteriaTo searchCriteria);
	
	public ResponseTo retrieveDealershipInfo();
}
