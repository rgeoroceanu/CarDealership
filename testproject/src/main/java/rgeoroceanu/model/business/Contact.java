package rgeoroceanu.model.business;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Contact {
	private String address;
	private String city;
	private String country;
	private String zip;
	private String phone;
	private String email;
}
