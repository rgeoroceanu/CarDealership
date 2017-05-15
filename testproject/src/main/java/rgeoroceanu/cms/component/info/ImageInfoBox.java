package rgeoroceanu.cms.component.info;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;

public class ImageInfoBox extends InfoBox {

	private static final long serialVersionUID = 1L;
	private final Image image;
	
	public ImageInfoBox() {
		super();
		image = initImage();
		setContent(image);
	}
	
	public void setImage(final String url) {
		image.setSource(new ExternalResource(url));
		image.setWidth(100, Unit.PERCENTAGE);
		image.setHeight(70, Unit.PERCENTAGE);
	}
	
	private Image initImage() {
		final Image image = new Image();
		return image;
	}
}
