package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Player;
import org.example.entities.Team;
import org.example.entities.attributes.MentalAttributes;
import org.example.entities.attributes.PhysicalAttributes;
import org.example.entities.attributes.TechnicalAttributes;
import org.example.enums.EPosition;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerModel {
	private Integer playerID;
	private String playerName;
	private String playerSurname;
	private Integer playerAge;
	private String playerNationality;
	private Integer playerOverallRating;
	private Double playerValue;
	private Double playerWage;
	private EPosition playersPosition;
	private TechnicalAttributes playerTechnicalAttributes;
	private MentalAttributes playerMentalAttributes;
	private PhysicalAttributes playerPhysicalAttributes;
	private Team playersTeam;



	public void displayPlayerInfo() {
		System.out.println("Player Name    			: " + this.playerName + " " + this.playerSurname);
		System.out.println("Age            			: " + this.playerAge);
		System.out.println("Nationality    			: " + this.playerNationality);
		System.out.println("Position       			: " + this.playersPosition);
		System.out.println("Market Value   			: " + this.playerValue);
		System.out.println("Wage           			: " + this.playerWage);
		System.out.println("Overall Rating 			: " + this.playerOverallRating);
		System.out.println("Technical Attributes 	: " + this.playerTechnicalAttributes);
		System.out.println("Mental Attributes 		: " + this.playerMentalAttributes);
		System.out.println("Physical Attributes 	: " + this.playerPhysicalAttributes);
		if (this.playersTeam!=null) {
			System.out.println("Club           		: " + this.playersTeam.getTeamName());
		} else {
			System.out.println("Club           		: Free Agent");
		}
		System.out.println("--------------------------------------------------");
	}
	

}