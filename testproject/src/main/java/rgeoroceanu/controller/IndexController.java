package rgeoroceanu.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for application information data retrieval.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Controller(value = "api")
public class IndexController {
	
	private static final Log LOG = LogFactory.getLog(IndexController.class);
	
	@RequestMapping(value = "/version.html", method = { RequestMethod.GET})
	public ResponseEntity<String> retrieveVersion() {
		LOG.info("Retrieved version number");
		return new ResponseEntity<String>(version, HttpStatus.OK);
	}
	
	@Autowired
	private String version;
	
}
