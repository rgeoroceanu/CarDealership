package rgeoroceanu.cardealership.service.exception;

/**
 * Exception used to handle an non-existing entity that was requested error.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class DataDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DataDoesNotExistException(final String message) {
		super(message);
	}
}
