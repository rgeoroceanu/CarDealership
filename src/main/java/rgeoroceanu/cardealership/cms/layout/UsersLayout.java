package rgeoroceanu.cardealership.cms.layout;

import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;

import rgeoroceanu.cardealership.cms.localization.Localizable;
import rgeoroceanu.cardealership.cms.localization.Localizer;
import rgeoroceanu.cardealership.model.business.User;

public class UsersLayout extends PageLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	private Grid<User> usersGrid;
	private Button addButton;
	
	public UsersLayout() {
		usersGrid = initGrid();
		addButton = initAddButton();
		final VerticalLayout layout = new VerticalLayout();
		layout.addComponent(addButton);
		layout.addComponent(usersGrid);
		layout.setSizeFull();
		this.setContent(layout);
	}
	
	public void addAddButtonListener(ClickListener listener) {
		addButton.addClickListener(listener);
	}
	
	public void setUsers(List<User> users) {
		usersGrid.setItems(users);
	}
	
	public void setVisibleColumns(String... columns) {
		usersGrid.setColumns(columns);
	}
	
	public void addUserClickListener(final ItemClickListener<User> itemClickListener) {
		usersGrid.addItemClickListener(itemClickListener);
	}
	
	private Grid<User> initGrid() {
		final Grid<User> grid = new Grid<>(User.class);
		grid.setSizeFull();
		return grid;
	}
	
	private Button initAddButton() {
		final Button button = new Button();
		button.addStyleName(ValoTheme.BUTTON_LINK);
		return button;
	}
	
	@Override
	public void localize() {
		super.localize();
		addButton.setCaption(Localizer.getLocalizedString("add"));
	}
}
