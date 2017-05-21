package rgeoroceanu.cms.component.info;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

public class ImageInfoBox extends InfoBox {

	private static final long serialVersionUID = 1L;
	private final Image image;
	
	public ImageInfoBox() {
		super();
		image = initImage();
		final VerticalLayout layout = new VerticalLayout();
		layout.addComponent(image);
		layout.setSizeFull();
		layout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		setContent(layout);
	}
	
	public void setImage(final String url) {
		if (url != null) {
			image.setSource(new ExternalResource(url));
		} else {
			image.setSource(null);
		}
	}
	
	private Image initImage() {
		final Image image = new Image();
		image.setWidth(100, Unit.PERCENTAGE);
		image.setHeightUndefined();
		return image;
	}
}
