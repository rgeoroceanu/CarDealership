package rgeoroceanu.service.exception;

public class DataDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DataDoesNotExistException(final String message) {
		super(message);
	}
}
