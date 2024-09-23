package org.example.entities.attributes;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.example.entities.BaseEntity;

@Data
@SuperBuilder
@Entity
@Table(name = "tblgoalkeeperattributes")

public class GKAttributes extends BaseEntity implements PlayerAttributes {

	private Integer reflexes;
	private Integer positioning;
	private Integer diving;
	private Integer oneOnOne;
	
	public GKAttributes(Integer reflexes, Integer positioning, Integer diving, Integer oneOnOne) {
		this.reflexes = reflexes;
		this.positioning = positioning;
		this.diving = diving;
		this.oneOnOne = oneOnOne;
	}
	
	public GKAttributes() {
	
	}
	
	public Integer getReflexes() {
		return reflexes;
	}
	
	public void setReflexes(Integer reflexes) {
		this.reflexes = reflexes;
	}
	
	public Integer getPositioning() {
		return positioning;
	}
	
	public void setPositioning(Integer positioning) {
		this.positioning = positioning;
	}
	
	public Integer getDiving() {
		return diving;
	}
	
	public void setDiving(Integer diving) {
		this.diving = diving;
	}
	
	public Integer getOneOnOne() {
		return oneOnOne;
	}
	
	public void setOneOnOne(Integer oneOnOne) {
		this.oneOnOne = oneOnOne;
	}
}