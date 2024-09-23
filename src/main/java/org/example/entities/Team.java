package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tblteams")
public class Team extends BaseEntity {
	
	
	@Column(length = 50, nullable = false, unique = true)
	private String teamName;
	private String teamLocation;
	private Double transferBudget;
	private Double wageBudget;
	private String stadiumName;
	@ManyToOne
	@JoinColumn(name = "league_id", referencedColumnName = "id")
	private League league;
	@Transient
	private Integer leagueID;
	
	
	public Team(Integer LeagueID, String teamName, String teamLocation, String stadiumName, Double transferBudget,
	            Double wageBudget) {
		this.leagueID = LeagueID;
		this.teamName = teamName;
		this.teamLocation = teamLocation;
		this.stadiumName = stadiumName;
		this.transferBudget = transferBudget;
		this.wageBudget = wageBudget;
		//DatabaseModels.teamDB.save(this);
	}
	
}