package rgeoroceanu.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="dealerships")
public class Dealership extends Base {
	@OneToMany
	private List<User> users;
	@Embedded
	private Contact contact;
	@OneToMany
	private List<Car> cars;
	
}