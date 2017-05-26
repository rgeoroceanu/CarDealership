package rgeoroceanu.model.business;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Contact {
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
	
	
}
