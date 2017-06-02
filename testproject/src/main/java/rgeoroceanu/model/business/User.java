package rgeoroceanu.model.business;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

import lombok.Getter;
import lombok.Setter;
import rgeoroceanu.model.type.Role;

@Getter
@Setter
@Entity
@Table(name="users")
public class User extends Base {
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();
	@Basic
	private String username;
	@Basic
	private String password;
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("super", super.toString()).add("roles", roles).add("username", username)
				.add("password", password).toString();
	}
}
