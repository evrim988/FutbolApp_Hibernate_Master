package org.example.service;


import org.example.entities.League;
import org.example.repository.LeagueRepository;

public class LeagueService extends ServiceManager<org.example.entities.League, Integer>  {

	private final LeagueRepository leagueRepository;

	private static LeagueService instance;
	
	private LeagueService() {
		this(new LeagueRepository());
	}
	
	private LeagueService(LeagueRepository repository) {
		super(repository);
		this.leagueRepository = repository;
	}

	public static LeagueService getInstance() {
		if (instance == null) {
			instance = new LeagueService();
		}
		return instance;
	}




}