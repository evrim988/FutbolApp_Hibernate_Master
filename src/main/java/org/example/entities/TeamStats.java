package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.modules.MatchModule;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name="tblteamstats")
public class TeamStats extends BaseEntity {

	private LocalDate lastUpdateDate;
	@ManyToOne
	@JoinColumn(name = "team_league_id", referencedColumnName = "id")
	private League teamLeague;
	@OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
	private Integer totalPoint=0;
	private Integer goalScored=0;
	private Integer goalConceded=0;
	private Integer average=0;
	private Integer gamesPlayed=0;
	private Integer gamesWon=0;
	private Integer gamesLost=0;
	private Integer gamesDrawn=0;
	
	public TeamStats(Integer teamID) {

	}
	
	@Override
	public void prePersist() {
		this.state = 1;
		this.createdAt = LocalDate.now();
		this.updatedAt = LocalDate.now();
		this.lastUpdateDate = LocalDate.of(2024,8,22);
	}
	


	

	
}