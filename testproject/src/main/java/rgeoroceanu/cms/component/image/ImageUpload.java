package rgeoroceanu.cms.component.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;

public class ImageUpload extends CustomComponent {

	private static final long serialVersionUID = 1L;
	private static final String ACCEPTED_MIME_TYPE = "image/jpeg";
	private final Upload upload;
	private File file;
	private ImageUploadedListener imageUploaderListener;

	public interface ImageUploadedListener {
		public void imageUploaded(File imageFile);
	}

	public ImageUpload() {
		upload = initUpload();
		this.setCompositionRoot(upload);
	}

	public void setImageUploadedListener(final ImageUploadedListener imageUploaderListener) {
		this.imageUploaderListener = imageUploaderListener;
	}
	
	public void handleUploadSucceeded(SucceededEvent event) {
		final BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(file);
		} catch(IOException e) {
			Notification.show("Cannot read file");
			return;
		}
		if (isValidImage(bufferedImage) == false) {
			Notification.show("Invalid image uploaded");
			return;
		}
		imageUploaderListener.imageUploaded(file);
	}

	public OutputStream handleReceiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null;
		try {
			file = File.createTempFile("temp", ".jpeg");
			fos = new FileOutputStream(file);
		} catch (IOException e) {
			return null;
		}
		return fos;
	}


	private void handleUploadStarted(StartedEvent event) {
		final String mimeType = event.getMIMEType().toLowerCase();
		if (mimeType.equals(ACCEPTED_MIME_TYPE) == false) {
			upload.interruptUpload();
			Notification.show("Only JPEG accepted");
		}

	}

	private Upload initUpload() {
		final Upload upload = new Upload();
		upload.setReceiver((filename, mimeType) -> handleReceiveUpload(filename, mimeType));
		upload.addStartedListener(e -> handleUploadStarted(e));
		upload.addSucceededListener(e -> handleUploadSucceeded(e));
		upload.setImmediate(true);
		upload.addStyleName("upload-with-icon");
		upload.setButtonCaption("+");
		return upload;
	}

	private boolean isValidImage(final BufferedImage bufferedImage) {
		return true;
	}
}
