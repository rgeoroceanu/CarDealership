package rgeoroceanu.cms.page;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import rgeoroceanu.cms.form.CarForm;

@Component
public class CarPage extends Page {

	private static final long serialVersionUID = 1L;
	private final CarForm carForm;
	
	
	public CarPage() {
		super();
		carForm = new CarForm();
		this.setContent(carForm);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
