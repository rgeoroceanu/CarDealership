package rgeoroceanu.cardealership.service.exception;

/**
 * Exception used to handle an image write error.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class ImageWriteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ImageWriteException(final String message) {
		super(message);
	}
}
