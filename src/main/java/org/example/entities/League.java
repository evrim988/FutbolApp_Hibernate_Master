package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.enums.ERegion;


import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "tblleague")
public class League extends BaseEntity {
	
	@Column(unique = true, nullable = false)
	private String leagueName;

	@Enumerated(EnumType.STRING)
	private ERegion region;

	private String season;
	private Integer division;

	@Temporal(TemporalType.DATE)
	private LocalDate seasonStartDate;
	private LocalDate seasonEndDate;

	@OneToMany(mappedBy = "league", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Team> leagueTeamList;

	@Transient
	private List<Integer> leagueTeamIDList;

	@Override
	public String toString() {
		return "League{" +
				"leagueName='" + getLeagueName() + '\'' +
				", region=" + getRegion() +
				", season='" + getSeason() + '\'' +
				", division=" + getDivision() +
				", seasonStartDate=" + getSeasonStartDate() +
				", seasonEndDate=" + getSeasonEndDate() +
				'}';
	}
}