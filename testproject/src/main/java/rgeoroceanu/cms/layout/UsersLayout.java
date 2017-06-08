package rgeoroceanu.cms.layout;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Grid;

import rgeoroceanu.cms.localization.Localizable;

public class UsersLayout extends PageLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	private Grid usersGrid;
	
	public UsersLayout() {
		usersGrid = new Grid();
		usersGrid.setSizeFull();
		this.setContent(usersGrid);
	}
	
	public void setUsersContainer(final BeanItemContainer<?> container) {
		usersGrid.setContainerDataSource(container);
	}
	
	public void setVisibleColumns(final Object[] columns) {
		usersGrid.setColumns(columns);
	}
	
	public void addUserClickListener(final ItemClickListener itemClickListener) {
		usersGrid.addItemClickListener(itemClickListener);
	}
	
	@Override
	public void localize() {
		super.localize();
	}
}
