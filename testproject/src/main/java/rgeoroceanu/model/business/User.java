package rgeoroceanu.model.business;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rgeoroceanu.model.type.Role;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
	
	private Long id;
	private Role role;
	private String username;
	private String password;

}
