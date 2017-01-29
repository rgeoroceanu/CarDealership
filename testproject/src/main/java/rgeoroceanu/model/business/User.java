package rgeoroceanu.model.business;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.Role;

@Getter
@Setter
public class User {
	
	private Long id;
	private Role role;
	private String username;
	private String password;

}
