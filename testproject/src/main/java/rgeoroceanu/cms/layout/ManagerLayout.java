package rgeoroceanu.cms.layout;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TabSheet;

import rgeoroceanu.cms.localization.Localizable;
import rgeoroceanu.cms.localization.Localizer;

public class ManagerLayout extends PageLayout implements Localizable {

	private static final long serialVersionUID = 1L;
	private final TabSheet tabSheet;
	private DealershipEditLayout dealershipLayout;
	private Grid usersGrid;
	
	public ManagerLayout() {
		tabSheet = new TabSheet();
		dealershipLayout = new DealershipEditLayout();
		usersGrid = new Grid();
		tabSheet.addTab(dealershipLayout);
		tabSheet.addTab(usersGrid);
		usersGrid.setSizeFull();
		this.setContent(tabSheet);
	}
	
	public void setUsersContainer(final BeanItemContainer<?> container) {
		usersGrid.setContainerDataSource(container);
	}
	
	public void setVisibleColumns(final Object[] columns) {
		usersGrid.setColumns(columns);
	}
	
	public DealershipEditLayout getDealershipEditLayout() {
		return dealershipLayout;
	}
	
	public void addSaveDealershipButtonListener(final ClickListener listener) {
		dealershipLayout.addSaveButtonListener(listener);
	}
	
	public void addDiscardDealershipButtonListener(final ClickListener listener) {
		dealershipLayout.addDiscardButtonListener(listener);
	}
	
	public void addUserClickListener(final ItemClickListener itemClickListener) {
		usersGrid.addItemClickListener(itemClickListener);
	}
	
	@Override
	public void localize() {
		super.localize();
		tabSheet.getTab(dealershipLayout).setCaption(Localizer.getLocalizedString("dealership_info"));
		tabSheet.getTab(usersGrid).setCaption(Localizer.getLocalizedString("users"));
	}
}
