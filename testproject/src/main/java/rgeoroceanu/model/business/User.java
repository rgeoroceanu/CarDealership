package rgeoroceanu.model.business;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.Role;

@Getter
@Setter
@Entity
@Table(name="users")
public class User extends Base {
	
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
	@Basic
	private String username;
	@Basic
	private String password;

}
