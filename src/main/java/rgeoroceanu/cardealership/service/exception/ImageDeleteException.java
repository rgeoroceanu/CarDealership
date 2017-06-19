package rgeoroceanu.service.exception;

/**
 * Exception used to handle an image deletion error.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class ImageDeleteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ImageDeleteException(final String message) {
		super(message);
	}
}
