package rgeoroceanu.model.business;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="purchases")
public class Purchase extends Base {
	
	@Lob
	private Car car;
	@Column
	private LocalDateTime purchaseDate;
	@Basic
	private int salePriceInEuro;
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("super", super.toString()).add("car", car)
				.add("purchaseDate", purchaseDate).add("salePriceInEuro", salePriceInEuro).toString();
	}
	
	
}