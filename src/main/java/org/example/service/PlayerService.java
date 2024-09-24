package org.example.service;


import org.example.entities.Player;
import org.example.entities.Team;
import org.example.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerService extends ServiceManager<Player, Integer>  {
	private final PlayerRepository playerRepository;


	private static PlayerService instance;
	
	private PlayerService() {
		this(new PlayerRepository());
	}
	
	private PlayerService(PlayerRepository repository) {
		super(repository);
		this.playerRepository = repository;

	}

	public static PlayerService getInstance() {
		if (instance == null) {
			instance = new PlayerService();
		}
		return instance;
	}


	/*public List<Player> findAllByTeamName(String teamName) {
		return playerRepository.findAllByTeamName(teamName);
	}
*/


	public List<Player> findAllByTeam(Integer id) {
		Optional<Team> team = TeamService.getInstance().findById(id);
		if (team.isPresent()) {
			Team teamX = team.get();
			List<Player> playerList = findByFieldNameAndValue("team", teamX);
			return playerList;
		}
		return new ArrayList<>();
	}



}