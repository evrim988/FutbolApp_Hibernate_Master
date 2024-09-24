package org.example.service;


import org.example.entities.League;
import org.example.entities.Manager;
import org.example.entities.Team;
import org.example.repository.ManagerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerService extends ServiceManager<Manager,Integer>  {
	private final ManagerRepository managerRepository;
	private LeagueService leagueService;
	private TeamService teamService;
	
	public ManagerService() {
		this(new ManagerRepository());
	}
	
	private ManagerService(ManagerRepository repository) {
		super(repository);
		this.managerRepository = repository;
	}

	public List<Manager> findAllByLeague(Integer id) {
		try{
			Optional<League> league = leagueService.findById(id);
			if (league.isPresent()) {
				League leagueX = league.get();
				List<Team> teamList = teamService.findByFieldNameAndValue("league", leagueX);
				for (Team team : teamList) {
					List<Manager> managerList = findByFieldNameAndValue("team", team);
					return managerList;

				}
			}
		}
		catch (Exception e){
			System.out.println("Service: Manager listelemede hata: " + e.getMessage());
		}

		return new ArrayList<>();
	}

	public Optional<Manager> findByUserNameAndPassword(String username, String password) {
		List<Manager> managerList = findAll();
		for (Manager manager : managerList) {
			if (manager.getManagerUserName().equals(username) && manager.getManagerPassword().equals(password)) {
				return Optional.of(manager);
			}
		}
		return Optional.empty();
	}
}