package org.example.service;


import org.example.entities.League;
import org.example.repository.LeagueRepository;

public class LeagueService extends ServiceManager<org.example.entities.League, Integer>  {

	private final org.example.repository.LeagueRepository leagueRepository;
	
	public LeagueService() {
		this(new LeagueRepository());
	}
	
	private LeagueService(LeagueRepository repository) {
		super(repository);
		this.leagueRepository = repository;
	}


}