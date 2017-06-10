package rgeoroceanu.cms.layout;

import java.util.List;

import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.ItemClickListener;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.model.business.User;

public class UsersLayout extends PageLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	private Grid<User> usersGrid;
	
	public UsersLayout() {
		usersGrid = new Grid<>(User.class);
		usersGrid.setSizeFull();
		this.setContent(usersGrid);
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
	
	@Override
	public void localize() {
		super.localize();
	}
}
