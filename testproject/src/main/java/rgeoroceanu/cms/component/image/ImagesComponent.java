package rgeoroceanu.cms.component.image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cms.component.image.ImageUpload.ImageUploadedListener;

/**
 * Component that displays a list of images and provides functionality for uploading more.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class ImagesComponent extends CustomComponent implements ImageUploadedListener {
	private static final long serialVersionUID = 1L;
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = 200;
	private final ImageUpload imageUpload;
	private final CssLayout imagesLayout;
	private List<File> uploadedImageFiles = new ArrayList<>();
	private List<String> removedImageUrls = new ArrayList<>();
	
	
	public ImagesComponent() {
		imageUpload = initImageUpload();
		imagesLayout = initImagesLayout();
		addUploadComponent();
		this.setCompositionRoot(imagesLayout);
	}
	
	/**
	 * Set images that are displayed as a list.
	 * @param imagesUrl
	 */
	public void setImages(final List<String> imagesUrl) {
		clear();
		imagesUrl.forEach(imageUrl -> addImageComponent(imageUrl));
	}
	
	/**
	 * Image uploaded event.
	 */
	@Override
	public void imageUploaded(File imageFile) {
		addImageComponent(imageFile);
		uploadedImageFiles.add(imageFile);
	}
	
	/**
	 * Retrieve the current uploaded image files.
	 * @return uploaded image files.
	 */
	public List<File> getUploadedImageFiles() {
		return uploadedImageFiles;
	}
	
	/**
	 * Retrieve the current removed image urls.
	 * @return urls of the images to be removed.
	 */
	public List<String> getRemovedImageUrls() {
		return removedImageUrls;
	}
	
	private void clear() {
		uploadedImageFiles.clear();
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
		final AbsoluteLayout layout = new AbsoluteLayout();
		image.setWidth(100, Unit.PERCENTAGE);
		image.setHeightUndefined();
		layout.addComponent(image, "left: 0%; right: 0%; top: 10%; bottom: 10%;");
		//layout.setComponentAlignment(image, Alignment.MIDDLE_LEFT);
		layout.setWidth(IMAGE_WIDTH, Unit.PIXELS);
		layout.setHeight(IMAGE_HEIGHT, Unit.PIXELS);
		layout.addStyleName("layout-padding");
		final Button removeButton = new Button();
		removeButton.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		removeButton.addStyleName(ValoTheme.BUTTON_SMALL);
		removeButton.setIcon(FontAwesome.REMOVE);
		removeButton.setWidth(30, Unit.PIXELS);
		removeButton.addClickListener(e -> handleRemove(image));
		layout.addComponent(removeButton, "right: 0px; top: 10%;");
		imagesLayout.addComponent(layout, imagesLayout.getComponentCount() - 1);
	}
	
	private void handleRemove(final Image image) {
		final ExternalResource resource = (ExternalResource) image.getSource();
		final String url = resource.getURL();
		removedImageUrls.add(url);
		imagesLayout.removeComponent(image.getParent());
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
