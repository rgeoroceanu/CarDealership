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
		image.setSource(new ExternalResource(url));
		image.setWidth(100, Unit.PERCENTAGE);
		image.setHeightUndefined();
	}
	
	private Image initImage() {
		final Image image = new Image();
		return image;
	}
}
