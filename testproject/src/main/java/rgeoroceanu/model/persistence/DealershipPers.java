package rgeoroceanu.model.persistence;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.business.Contact;

@Getter
@Setter
@Entity
@Table(name="dealerships")
public class DealershipPers {
	@OneToMany
	private List<UserPers> users;
	@Embedded
	private Contact contact;
	@OneToMany
	private List<VehiclePers> vehicles;
	
}
