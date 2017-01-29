package rgeoroceanu.model.persistence;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Getter
@Setter
@Entity
@Table(name="purchases")
public class PurchasePers extends BasePers {
	
	@Lob
	private VehiclePers vehicle;
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime purchaseDate;
	@Basic
	private int price;

}
