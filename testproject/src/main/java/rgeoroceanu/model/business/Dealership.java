package rgeoroceanu.model.business;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Dealership {
	
	private Long id;
	private List<User> users = new ArrayList<User>();
	private Contact address;
	private List<Vehicle> vehicles = new ArrayList<>();
	
}
