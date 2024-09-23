package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.enums.EMatchStatus;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tblmatch")
public class Match extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "home_team_id", referencedColumnName = "id")
	private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
	private Team awayTeam;

	private LocalDate matchDate;

	@Enumerated(EnumType.STRING)
	private EMatchStatus status;

	@ManyToOne
	@JoinColumn(name = "league_id", referencedColumnName = "id")
    private League league;

	private Integer homeTeamScore;
	private Integer awayTeamScore;

	@Transient
	private Ball ball;
	
	public Match(Integer homeTeamId, Integer awayTeamId, LocalDate matchDate, EMatchStatus matchStatus, Integer leagueID) {
	}
}