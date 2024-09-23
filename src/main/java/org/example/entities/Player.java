package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.example.entities.attributes.GKAttributes;
import org.example.entities.attributes.MentalAttributes;
import org.example.entities.attributes.PhysicalAttributes;
import org.example.entities.attributes.TechnicalAttributes;
import org.example.enums.EPosition;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name="tblplayer")
public class Player extends Person {
	
	private Integer playerOverallRating;

	@ManyToOne
	@JoinColumn(name = "team_id", referencedColumnName = "id")
	private Team team;

	private Double playerValue;
	private Double playerWage;

	@Enumerated(EnumType.STRING)
	private EPosition playersPosition;

	@ManyToOne
	@JoinColumn(name = "technical_attributes_id", referencedColumnName = "id")
	private TechnicalAttributes playerTechnicalAttributes;

	@ManyToOne
    @JoinColumn(name = "mental_attributes_id", referencedColumnName = "id")
    private MentalAttributes playerMentalAttributes;

	@ManyToOne
    @JoinColumn(name = "physical_attributes_id", referencedColumnName = "id")
    private PhysicalAttributes playerPhysicalAttributes;

	@ManyToOne
    @JoinColumn(name = "gk_attributes_id", referencedColumnName = "id")
    private GKAttributes gkAttributes;
	
	
	public Player(
	              TechnicalAttributes technicalAttributes, MentalAttributes mentalAttributes, PhysicalAttributes physicalAttributes,
	              Double playerValue, Double playerWage, EPosition EplayersPosition) {
		this.playerValue = playerValue;
		this.playerWage = playerWage;
		this.playersPosition = EplayersPosition;
		this.playerTechnicalAttributes = technicalAttributes;
		this.playerMentalAttributes = mentalAttributes;
		this.playerPhysicalAttributes = physicalAttributes;
		this.playerOverallRating=calculateOverallRating();
		//DatabaseModels.playerDB.save(this);
	}
	
	public Player(GKAttributes gkAttributes,
	              Double playerValue, Double playerWage) {
		this.playerValue = playerValue;
		this.playerWage = playerWage;
		this.playersPosition = EPosition.GK;
		this.gkAttributes = gkAttributes;
		this.playerOverallRating = calculateOverallRating();
		//DatabaseModels.playerDB.save(this);
	}
	
	public Player(String name, String surname, Integer age, String nationality, TechnicalAttributes ta, MentalAttributes ma, PhysicalAttributes pa, int id, Double value, Double wage, EPosition position) {
	}
	
	public Player(String name, String surnameGK, Integer ageGK, String nationality, GKAttributes ga, int id, Double value, Double wage) {
	}
	
	
	private Integer calculateOverallRating() {
		if(playersPosition.equals(EPosition.ST)){
			// Forwards - emphasize on Finishing, Shot Power, and Dribbling
			this.playerOverallRating = (int) (
					0.4 * playerTechnicalAttributes.getFinishing() +
							0.3 * playerTechnicalAttributes.getShotPower() +
							0.2 * playerTechnicalAttributes.getDribbling() +
							0.1 * playerPhysicalAttributes.getSpeed()
			);
		}
		else if(playersPosition.equals(EPosition.CM)){
			// Midfielders - emphasize on Passing, Vision, and Dribbling
			this.playerOverallRating = (int) (
					0.35 * playerTechnicalAttributes.getPass() +
							0.2 * playerTechnicalAttributes.getDribbling() +
							0.15 * playerTechnicalAttributes.getTackle() +
							0.1 * playerMentalAttributes.getVision() +
							0.1 * playerPhysicalAttributes.getStamina() +
							0.1 * playerTechnicalAttributes.getFinishing()
			);
		}
		else if(playersPosition.equals(EPosition.CB)){
			// Defenders - emphasize on Tackling, Strength, and Heading
			this.playerOverallRating = (int) (
					0.4 * playerTechnicalAttributes.getTackle() +
							0.2 * playerTechnicalAttributes.getHeader() +
							0.2 * playerPhysicalAttributes.getStrength() +
							0.1 * playerPhysicalAttributes.getJumping() +
							0.1 * playerMentalAttributes.getDecisionMaking()
			);
		}
		else if(playersPosition.equals(EPosition.GK)){
			this.playerOverallRating = (int) (
					0.35 * gkAttributes.getPositioning() +
							0.3 * gkAttributes.getReflexes() +
							0.2 * gkAttributes.getDiving() +
							0.15 * gkAttributes.getOneOnOne()
			);
		}
		// Include other positions like LW, RW, LB, RB as needed
		else if(playersPosition.equals(EPosition.LW) || playersPosition.equals(EPosition.RW)){
			// Wingers - emphasize on Speed, Crossing, and Dribbling
			this.playerOverallRating = (int) (
					0.3 * playerTechnicalAttributes.getDribbling() +
							0.25 * playerTechnicalAttributes.getCrossing() +
							0.2 * playerPhysicalAttributes.getSpeed() +
							0.15 * playerTechnicalAttributes.getFinishing() +
							0.1 * playerMentalAttributes.getVision()
			);
		}
		else if(playersPosition.equals(EPosition.LB) || playersPosition.equals(EPosition.RB)){
			// Fullbacks - emphasize on Tackling, Speed, and Stamina
			this.playerOverallRating = (int) (
					0.3 * playerTechnicalAttributes.getTackle() +
							0.25 * playerPhysicalAttributes.getSpeed() +
							0.2 * playerPhysicalAttributes.getStamina() +
							0.15 * playerTechnicalAttributes.getCrossing() +
							0.1 * playerMentalAttributes.getDecisionMaking()
			);
		}
		
		return playerOverallRating;
	}
	
}