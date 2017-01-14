package rgeoroceanu.model.business;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
	private Long id;
	private List<User> users = new ArrayList<User>();
	private Address address;
	private List<Vehicle> vehicles = new ArrayList<>();
}
