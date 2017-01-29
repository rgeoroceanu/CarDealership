package rgeoroceanu.model.business;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dealership {
	
	private Long id;
	private List<User> users = new ArrayList<User>();
	private Contact contact;
	private List<Vehicle> vehicles = new ArrayList<>();
	
}
