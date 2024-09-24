package org.example.modules;

import org.example.entities.League;
import org.example.entities.Manager;
import org.example.entities.Player;
import org.example.entities.Team;
import org.example.models.DatabaseModels;
import org.example.models.TeamModel;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TeamModule {
	static Scanner sc = new Scanner(System.in);
	
	
	public static void startTeamMenu() {
		int userInput;
		do {
			userInput = teamMenuEntry();
			teamMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int teamMenuEntry() {
		int userInput=-1;
		boolean validInput = false;
		
		while(!validInput) {
			System.out.println("\n---------Team Menu--------------");
			System.out.println("1-List of Teams");
			System.out.println("3-Find Team by Name");
			System.out.println("4-Played Matches of a Team");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if(userInput >= 0 && userInput <= 4) {
					validInput = true;
				}
				else {
					System.out.println("\nPlease enter a valid option!");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter a numeric value!");
				sc.next();
			}
		}
		return userInput;
	}
	
	private static void teamMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayAllTeams(LogInModule.loggedManager);
			case 3 -> displayTeamByName();
			case 4 -> displayPlayedMatchesTeam();
			case 0 -> System.out.println("\nReturning to Main Menu...\n");
			default-> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void displayPlayedMatchesTeam() {
		displayTeams();
		System.out.println("Enter a Team ID to view their played matches: ");
		Integer teamID = sc.nextInt();
		Optional<Team> byID = DatabaseModels.teamController.findById(teamID);
		if (byID.isPresent()) {
			TeamModel teamModel = new TeamModel(DatabaseModels.getInstance(), byID.get());
			teamModel.displayConcludedMatchesofaTeam();
		}
		
	}
	
	
	private static void displayTeamByName() {
		System.out.print("\nEnter the Team Name (0=Back to Team Menu): ");
		String teamName = sc.nextLine();
		
		List<Team> byTeamName = DatabaseModels.teamController.findByFieldNameAndValue("teamName", teamName);
		if (byTeamName.isEmpty()) {
			System.out.println("Team not found!");
			return;
		}
		if (teamName.equalsIgnoreCase("0")) {
			return;
		}
		System.out.println();
		if(teamName.equalsIgnoreCase("BYE")){
			System.out.println("Team not found!");
			return;
		}
		else{
			for(Team team : byTeamName) {
				System.out.println(team.getId() + " - " + team.getTeamName());
			}

		}
		displayTeamDetails();
	}
	

	public static void displayAllTeams(Manager manager) {
		Integer leagueID = LeagueModule.validLeagueIDControl();
		System.out.println("\n-------------List of Teams-----------------------------------");
		List<Team> teams = DatabaseModels.teamController.findAllByLeague(leagueID);
		Optional<Team> byID = DatabaseModels.teamController.findById(manager.getTeam().getId());
		if(byID.isPresent()) {
			System.out.println("Manager's Current Team:  ");
			System.out.println(byID.get());
		}
		/*System.out.println("\nOther Teams:  ");
		teams.stream().filter(team -> team.getId()!= manager.getTeam().getId() && !(team.getTeamName().equals("BYE"))).forEach(team -> System.out.println("Team Name: " + team.getTeamName()));
		*/
		displayTeams();
		displayTeamDetails();
	}
	
	public static void displayTeamDetails() {
		try {
			System.out.print("\nWhich team do you want to select? Please enter the Team ID (0=Back to Team Menu): ");
			Integer teamID = sc.nextInt();
			if(teamID==0){
				return;
			}
			Optional<Team> teamByID = DatabaseModels.teamController.findById(teamID);
			if (teamByID.isPresent()) {
				TeamModel tm=new TeamModel(DatabaseModels.getInstance(), teamByID.get());
				System.out.println("\nTeam Details:  ");
				tm.displayClubInfo();
			}
			else {
				System.out.println("There is no team by that ID.");
				return;
			}
			List<Player> players = DatabaseModels.playerController.findAllByTeam(teamID);
			if (players.isEmpty()) {
				System.out.println("No players found for this team.");
			} else {
				System.out.println("\nTeam Players:  ");
				for (Player player:players){
					System.out.println(player);
				}

			}
		}catch (InputMismatchException e){
			System.out.println("\nPlease enter a numeric value!");
			sc.next();
		}

	}

	public static void displayTeams(){

		for(Team team : DatabaseModels.teamController.findAll()) {
			if (team.getTeamName().equalsIgnoreCase("BYE")){
				continue;
			}

			System.out.println(team.getId() + " - " + team.getTeamName());

		}
	}
}