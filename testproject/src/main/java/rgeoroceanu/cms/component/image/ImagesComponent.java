package rgeoroceanu.cms.component.image;

import java.io.File;
import java.util.List;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

import rgeoroceanu.cms.component.image.ImageUpload.ImageUploadedListener;

public class ImagesComponent extends CustomComponent implements ImageUploadedListener {
	private static final long serialVersionUID = 1L;
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = 200;
	private final ImageUpload imageUpload;
	private final CssLayout imagesLayout;
	
	
	public ImagesComponent() {
		imageUpload = initImageUpload();
		imagesLayout = initImagesLayout();
		addUploadComponent();
		this.setCompositionRoot(imagesLayout);
	}
	
	public void setImages(final List<String> imagesUrl) {
		clear();
		imagesUrl.forEach(imageUrl -> addImageComponent(imageUrl));
	}
	
	@Override
	public void imageUploaded(File imageFile) {
		addImageComponent(imageFile);
	}
	
	private void clear() {
		imagesLayout.removeAllComponents();
		addUploadComponent();
	}
	
	private void addImageComponent(final File file) {
		final Image image = new Image(null, new FileResource(file));
		initImageLayout(image);
	}
	
	private void addImageComponent(final String imageUrl) {
		final Image image = new Image(null, new ExternalResource(imageUrl));
		initImageLayout(image);
	}
	
	private void initImageLayout(final Image image) {
		final VerticalLayout layout = new VerticalLayout();
		image.setWidth(100, Unit.PERCENTAGE);
		image.setHeightUndefined();
		layout.addComponent(image);
		layout.setComponentAlignment(image, Alignment.MIDDLE_LEFT);
		layout.setWidth(IMAGE_WIDTH, Unit.PIXELS);
		layout.setHeight(IMAGE_HEIGHT, Unit.PIXELS);
		layout.addStyleName("layout-padding");
		imagesLayout.addComponent(layout, imagesLayout.getComponentCount() - 1);
	}
	
	private void addUploadComponent() {
		final VerticalLayout layout = new VerticalLayout();
		layout.setWidth(IMAGE_WIDTH, Unit.PIXELS);
		layout.setHeight(IMAGE_HEIGHT, Unit.PIXELS);
		layout.addComponent(imageUpload);
		layout.setComponentAlignment(imageUpload, Alignment.MIDDLE_CENTER);
		layout.addStyleName("layout-padding");
		imageUpload.setWidthUndefined();
		imagesLayout.addComponent(layout);
	}
	
	private CssLayout initImagesLayout() {
		final CssLayout layout = new CssLayout();
		layout.setWidth(100, Unit.PERCENTAGE);
		return layout;
	}
	
	private ImageUpload initImageUpload() {
		final ImageUpload upload = new ImageUpload();
		upload.setImageUploadedListener(this);
		return upload;
	}
}
