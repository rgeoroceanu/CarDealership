package rgeoroceanu.cardealership.model.business;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="dealership_info")
public class Dealership extends Base {
	
	@Basic
	@NotEmpty
	private String name;
	@Basic
	private String address;
	@Basic
	private String city;
	@Basic
	private String country;
	@Basic
	private String zip;
	@Basic
	private String phone;
	@Basic
	private String email;
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("super", super.toString()).add("name", name).add("address", address)
				.add("city", city).add("country", country).add("zip", zip).add("phone", phone).add("email", email)
				.toString();
	}
}