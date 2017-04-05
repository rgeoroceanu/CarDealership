package rgeoroceanu.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="purchases")
public class Purchase extends Base {
	
	@Lob
	private Car car;
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime purchaseDate;
	@Embedded
	private Price price;

}
