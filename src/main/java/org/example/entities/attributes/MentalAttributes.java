package org.example.entities.attributes;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.example.entities.BaseEntity;

@Data
@SuperBuilder
@Entity
@Table(name = "tblmentalattributes")
public class MentalAttributes extends BaseEntity implements PlayerAttributes{
	
	private Integer composure;
	private Integer vision;
	private Integer decisionMaking;
	
	
	public MentalAttributes(Integer composure, Integer vision, Integer decisionMaking) {
		this.composure = composure;
		this.vision = vision;
		this.decisionMaking = decisionMaking;
	}
	
	public MentalAttributes() {
	
	}
	
	public Integer getComposure() {
		return composure;
	}
	
	public void setComposure(Integer composure) {
		this.composure = composure;
	}
	
	public Integer getVision() {
		return vision;
	}
	
	public void setVision(Integer vision) {
		this.vision = vision;
	}
	
	public Integer getDecisionMaking() {
		return decisionMaking;
	}
	
	public void setDecisionMaking(Integer decisionMaking) {
		this.decisionMaking = decisionMaking;
	}
}