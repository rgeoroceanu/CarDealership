package rgeoroceanu.cms.page;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;

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
@UIScope
@SpringView
public class UsersPage extends Page {

	private static final long serialVersionUID = 1L;
	private final UsersLayout usersLayout;

	public UsersPage() {
		super();
		usersLayout = new UsersLayout();
		usersLayout.setVisibleColumns("username", "roles");
		usersLayout.addUserClickListener(e -> App.getCurrent().navigateToUserPage(e.getItem().getId()));
		usersLayout.setContentWidth(950, Unit.PIXELS);
		usersLayout.alignCenterContent();
		usersLayout.setContentBorderless();
		usersLayout.addAddButtonListener(click -> App.getCurrent().navigateToUserPage(null));
		this.setLayout(usersLayout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		if (App.getCurrent().isAdmin() == false) {
			App.getCurrent().navigateToErrorPage("Permission denied!");
			return;
		}
		open();
	}
	
	private void open() {
		final List<User> users = dataService.getAllUsers();
		usersLayout.setUsers(users);
	}
}
