package org.example.service;


import org.example.entities.League;
import org.example.entities.Match;
import org.example.entities.Team;
import org.example.repository.MatchRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchService extends ServiceManager<Match, Integer>  {
	private final MatchRepository matchRepository;
	private final TeamService teamService;
	private final LeagueService leagueService;

	private static MatchService instance;

	private MatchService() {
		this(new MatchRepository());
	}
	
	private MatchService(MatchRepository repository) {
		super(repository);
		this.matchRepository = repository;
		this.teamService = TeamService.getInstance();
		this.leagueService = LeagueService.getInstance();
	}

	public static MatchService getInstance() {
		if (instance == null) {
			instance = new MatchService();
		}
		return instance;
	}

	public List<Match> findAllByTeamId(Integer teamId) {
		try {
			Optional<Team> optionalTeam = teamService.findById(teamId);
			if(optionalTeam.isPresent()) {
				Team team = optionalTeam.get();
				List<Match> matchList = findAll();
				for(Match match : matchList) {
					if (match.getHomeTeam().equals(team) && match.getAwayTeam().equals(team)) {
						return matchList;
					}
				}
			}

		}
		catch (Exception e) {
			System.out.println("Service:  Match listelemede sorun oluştu..." + e.getMessage());
		}
		return new ArrayList<>();
	}

	public List<Match> findAllByLeagueId(Integer leagueId) {
		try {
			Optional<League> OptionalLeague = leagueService.findById(leagueId);
			if (OptionalLeague.isPresent()) {
				League league = OptionalLeague.get();
				List<Match> matchList = findByFieldNameAndValue("league", league);
				return matchList;
			}
		}
		catch (Exception e) {
			System.out.println("Service: Match Listelemede Sorun Oluştu..." + e.getMessage());
		}
		return new ArrayList<>();
	}
}