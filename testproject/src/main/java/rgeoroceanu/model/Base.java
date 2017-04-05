package rgeoroceanu.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Getter
@Setter
@MappedSuperclass
public class Base {
	
	@Id
	private Long id;
	@Version
	private int version;
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime created;
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updated;
	
	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
	    updated = DateTime.now();
	    if (null == created) {
	    	created = DateTime.now();
	    }
	}
}
