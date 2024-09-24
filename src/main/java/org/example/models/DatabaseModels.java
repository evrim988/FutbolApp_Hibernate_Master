package org.example.models;


import org.example.controller.*;

public class DatabaseModels {
	
	private static DatabaseModels instance = new DatabaseModels();
	
	public static LeagueController leagueController = new LeagueController();
	public static TeamController teamController = new TeamController();
	public static MatchController matchController = new MatchController();
	public static ManagerController managerController = new ManagerController();
	public static PlayerController	playerController = new PlayerController();
	public static TeamStatController teamStatController = new TeamStatController();
	public static GameDateController gameDateController = new GameDateController();
	
	
	private DatabaseModels() {

	}
	
	public static DatabaseModels getInstance() {
		return instance;
	}

}