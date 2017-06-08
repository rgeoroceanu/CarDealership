package rgeoroceanu.cms.page;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import rgeoroceanu.cms.App;
import rgeoroceanu.cms.layout.UsersLayout;
import rgeoroceanu.model.business.User;

/**
 * Page used for managing users and general dealership information.
 * 
 * @author Radu Georoceanu <rgeoroceanu@yahoo.com>
 *
 */
@Component
public class UsersPage extends Page {

	private static final long serialVersionUID = 1L;
	private final UsersLayout usersLayout;
	private final BeanItemContainer<User> usersContainer;

	public UsersPage() {
		super();
		usersLayout = new UsersLayout();
		usersContainer = new BeanItemContainer<>(User.class);
		usersLayout.setUsersContainer(usersContainer);
		usersLayout.setVisibleColumns(new Object[] {"username", "roles"});
		usersLayout.addUserClickListener(e -> App.getCurrent().navigateToUserPage(((User) e.getItemId()).getId()));
		usersLayout.setContentWidth(850, Unit.PIXELS);
		usersLayout.alignCenterContent();
		usersLayout.setContentBorderless();
		this.setLayout(usersLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		checkPermissions();
		open();
	}

	private void checkPermissions() {

	}
	
	private void open() {
		final List<User> users = dataService.getAllUsers();
		usersContainer.removeAllItems();
		usersContainer.addAll(users);
		
	}
}
