package org.example.service;


import org.example.entities.League;
import org.example.entities.Manager;
import org.example.entities.Player;
import org.example.entities.Team;
import org.example.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamService extends ServiceManager<Team, Integer>  {
	private final TeamRepository teamRepository;
	private final LeagueService leagueService;
	private final PlayerService playerService;

	private static TeamService instance;

	private TeamService() {
		this(new TeamRepository());
	}
	
	private TeamService(TeamRepository repository) {
		super(repository);
		this.teamRepository = repository;
		this.playerService = PlayerService.getInstance();
		this.leagueService = LeagueService.getInstance();
	}

	public static TeamService getInstance() {
		if (instance == null) {
			instance = new TeamService();
		}
		return instance;
	}

	public List<Team> findAllByLeague(Integer id) {
		Optional<League> league = leagueService.findById(id);
		if (league.isPresent()) {
			League leagueX = league.get();
			List<Team> teamList = findByFieldNameAndValue("league", leagueX);
			return teamList;
		}
		return new ArrayList<>();
	}

	public Optional<Team> findByTeamName(String teamName) {
		try {
			return teamRepository.findByTeamName(teamName);
		}
		catch (Exception e) {
			System.out.println("Service: Takım Bulmada Hata: " + e.getMessage());
		}
		return Optional.empty();
	}

	public List<Team> findByFieldNameAndValueEqual(String fieldName, Object value) {
		return teamRepository.findByFieldNameAndValueEqual(fieldName, value);
	}
}