package rgeoroceanu.model.business;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Contact {
	private String address;
	private String city;
	private String country;
	private String zip;
	private String phone;
	private String email;
}
