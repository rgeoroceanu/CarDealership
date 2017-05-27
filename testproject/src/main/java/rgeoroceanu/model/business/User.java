package rgeoroceanu.model.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	@ElementCollection
	@CollectionTable(name = "roles")
	@Enumerated(EnumType.STRING)
	private List<Role> roles = new ArrayList<>();
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
