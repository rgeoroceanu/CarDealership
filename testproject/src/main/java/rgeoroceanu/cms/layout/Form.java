package rgeoroceanu.cms.layout;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;

public abstract class Form extends VerticalLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	private final Button saveButton;
	private final Button discardButton;
	private final Button removeButton;
	private SaveButtonListener saveButtonListener;
	private RemoveButtonListener removeButtonListener;
	private DiscardButtonListener discardButtonListener;
	
	public interface SaveButtonListener {
		public void handleSave();
	}
	
	public interface RemoveButtonListener {
		public void handleRemove();
	}
	
	public interface DiscardButtonListener {
		public void handleDiscard();
	}
	
	public Form() {
		saveButton = new Button();
		saveButton.addStyleName(ValoTheme.BUTTON_LINK);
		discardButton = new Button();
		discardButton.addStyleName(ValoTheme.BUTTON_LINK);
		removeButton = new Button();
		removeButton.addStyleName(ValoTheme.BUTTON_LINK);
		
		final HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.addComponent(saveButton);
		buttonLayout.addComponent(discardButton);
		buttonLayout.addComponent(removeButton);
		
		this.addComponent(buttonLayout);
	}
	
	public void setSaveButtonListener(final SaveButtonListener saveButtonListener) {
		this.saveButtonListener = saveButtonListener;
		saveButton.getListeners(ClickEvent.class).forEach(listener -> saveButton.removeClickListener((ClickListener) listener));
		saveButton.addClickListener(listener -> saveButtonListener.handleSave());
	}
	
	public void setRemoveButtonListener(final RemoveButtonListener removeButtonListener) {
		this.removeButtonListener = removeButtonListener;
		removeButton.getListeners(ClickEvent.class).forEach(listener -> removeButton.removeClickListener((ClickListener) listener));
		removeButton.addClickListener(listener -> removeButtonListener.handleRemove());
	}
	
	public void setDiscardButtonListener(final DiscardButtonListener discardButtonListener) {
		this.discardButtonListener = discardButtonListener;
		discardButton.getListeners(ClickEvent.class).forEach(listener -> discardButton.removeClickListener((ClickListener) listener));
		discardButton.addClickListener(listener -> discardButtonListener.handleDiscard());
	}
	
	public void setActionButtonsEnableState(boolean saveButtonEnabled,
			boolean discardButtonEnabled, boolean removeButtonEnabled) {
		
		saveButton.setEnabled(saveButtonEnabled);
		discardButton.setEnabled(saveButtonEnabled);
		removeButton.setEnabled(saveButtonEnabled);
	}
	
	public SaveButtonListener getSaveButtonListener() {
		return saveButtonListener;
	}

	public RemoveButtonListener getRemoveButtonListener() {
		return removeButtonListener;
	}

	public DiscardButtonListener getDiscardButtonListener() {
		return discardButtonListener;
	}

	@Override
	public void localize() {
		saveButton.setCaption(Localizer.getLocalizedString("save"));
		discardButton.setCaption(Localizer.getLocalizedString("discard"));
		removeButton.setCaption(Localizer.getLocalizedString("remove"));
	}
	
}
