package rgeoroceanu.cardealership.cms.component.image;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Image box that provides next and previous button to scroll through a
 * list of images.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
public class ImagePreview extends CustomComponent {
	
	private static final long serialVersionUID = 1L;
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = 200;
	private final Image image;
	private final Button leftButton;
	private final Button rightButton;
	private final List<Resource> imageResources = new ArrayList<>();
	private int currentIndex = 0;
	
	public ImagePreview() {
		image = initImage();
		leftButton = initLeftButton();
		rightButton = initRightButton();
		final AbsoluteLayout layout = initLayout();
		this.setCompositionRoot(layout);
	}
	
	/**
	 * Set list of image resources that can be displayed.
	 * @param imageResources of the images that can be displayed in the preview box.
	 */
	public void setImageResources(final List<Resource> imageResources) {
		this.imageResources.clear();
		this.imageResources.addAll(imageResources);
		displayImage(0);
	}
	
	private void displayImage(final int index) {
		if (index < imageResources.size()) {
			image.setSource(imageResources.get(index));
			currentIndex = index;
		}
	}
	
	private Image initImage() {
		final Image image = new Image();
		image.setWidth(IMAGE_WIDTH, Unit.PIXELS);
		image.setHeightUndefined();
		return image;
	}
	
	private AbsoluteLayout initLayout() {
		final VerticalLayout imageLayout = new VerticalLayout();
		imageLayout.addComponent(image);
		imageLayout.setMargin(false);
		imageLayout.setWidth(IMAGE_WIDTH, Unit.PIXELS);
		imageLayout.setHeight(IMAGE_HEIGHT, Unit.PIXELS);
		imageLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		final AbsoluteLayout layout = new AbsoluteLayout();
		layout.addComponent(imageLayout, "top:0px;left:0px");
		layout.addComponent(leftButton, "top:100px;left:0px");
		layout.addComponent(rightButton, "top:100px;right:0px");
		layout.setWidth(IMAGE_WIDTH, Unit.PIXELS);
		layout.setHeight(IMAGE_HEIGHT, Unit.PIXELS);
		return layout;
	}
	
	private Button initLeftButton() {
		final Button button = new Button();
		button.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		button.setIcon(VaadinIcons.CHEVRON_CIRCLE_LEFT_O);
		button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		button.addClickListener(e -> {
			decrementIndex();
			displayImage(currentIndex);
		});
		return button;
	}
	
	private Button initRightButton() {
		final Button button = new Button();
		button.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		button.setIcon(VaadinIcons.CHEVRON_CIRCLE_RIGHT_O);
		button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		button.addClickListener(e -> {
			incrementIndex();
			displayImage(currentIndex);
		});
		return button;
	}
	
	private void incrementIndex() {
		currentIndex++;
		if(currentIndex == imageResources.size()) {
			currentIndex = 0;
		}
	}
	
	private void decrementIndex() {
		currentIndex--;
		if(currentIndex == -1) {
			currentIndex = imageResources.size() -1;
		}
	}
}
