package rgeoroceanu.model.persistence;

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
public class UserPers extends BasePers {
	
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
	@Basic
	private String username;
	@Basic
	private String password;

}
