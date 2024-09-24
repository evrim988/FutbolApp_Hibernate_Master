package org.example.modules;



import org.example.entities.League;
import org.example.models.DatabaseModels;
import org.example.models.LeagueModel;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LeagueModule {
	static Scanner sc = new Scanner(System.in);
	
	
	public static void startLeagueMenu() {
		int userInput;
		do {
			userInput = leagueMenuEntry();
			leagueMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int leagueMenuEntry() {
		int userInput=-1;
		boolean validInput = false;
		
		while(!validInput) {
			System.out.println("\n---------League Menu--------------");
			System.out.println("1-List of Leagues");
			System.out.println("2-Find League by ID");
			System.out.println("3-Find League by Name");
			System.out.println("4-League Standing Table");
			System.out.println("5-Concluded Matches of the Selected League");
			System.out.println("0-FootballApp.Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if(userInput >= 0 && userInput <= 5) {
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
	
	private static void leagueMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayAllLeagues();
			case 2 -> displayLeagueByID();
			case 3 -> displayLeagueByName();
			case 4 -> displayStandingTable();
			case 5 -> displayConcludedMatches();
			case 0 -> System.out.println("\nReturning to FootballApp.Main Menu...\n");
			default-> System.out.println("Please enter a valid value!");
		}
	}
	
	private static void displayConcludedMatches() {
		Integer leagueID = null;
		boolean validInput = false;
		do {
			for(League league : DatabaseModels.leagueController.findAll()) {
				int count = 1;
				System.out.println(count + " - " + league.getLeagueName());
			}
			System.out.println("Enter a League Number to display Concluded Matches: (0=Back to Team Menu)");
			try {
				leagueID = sc.nextInt();
				if (leagueID == 0) {
					return;
				}
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a numeric value!");
				sc.nextLine();
			}
		} while (!validInput);
		
		Optional<League> byID = DatabaseModels.leagueController.findById(leagueID);
		if (byID.isPresent()) {
			LeagueModel leagueModel = new LeagueModel(DatabaseModels.getInstance(), byID.get());
			leagueModel.displayConcludedMatches();
		} else {
			System.out.println("League not found!");
		}
	}
	
	
	private static void displayStandingTable() {
		Integer leagueID = null;
		boolean validInput = false;
		
		do {
			for(League league : DatabaseModels.leagueController.findAll()) {
				int count = 1;
				System.out.println(count + " - " + league.getLeagueName());
			}
			System.out.println("\nEnter a League Number to display Standing Table: (0=Back to Team Menu)");
			try {
				leagueID = sc.nextInt();
				sc.nextLine();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter a numeric value!");
				sc.nextLine();
			}
		} while (!validInput);
		
		if (leagueID == 0) {
			return;
		}
		
		Optional<League> byID = DatabaseModels.leagueController.findById(leagueID);
		if (byID.isPresent()) {
			LeagueModel leagueModel = new LeagueModel(DatabaseModels.getInstance(), byID.get());
			leagueModel.displayStandingTable();
		} else {
			System.out.println("Please enter a valid ID!");
		}
	}
	
	
	private static void displayLeagueByName() {
		System.out.println("Enter a League Name: (0=Back to Team Menu)");
		String leagueName=sc.nextLine();
		if(leagueName.equalsIgnoreCase("0")){
			return;
		}
		List<League> byPartialLeagueName = DatabaseModels.leagueController.findByFieldNameAndValue("leagueName", leagueName);
		if(byPartialLeagueName.isEmpty()){
			System.out.println("\nNo league found with the name you've entered!");
			return;
		}
		else {
			System.out.println(byPartialLeagueName.size()+" results found!");
			for (League league:byPartialLeagueName){
				System.out.println("\n"+"LeagueID: "+league.getId()+"- League Name: "+league.getLeagueName());
			}
		}
		displayLeagueByDetails();
		
	}
	
	private static void displayLeagueByDetails() {
		Integer leagueID = null;
		
		while (true) {
			try {
				for(League league : DatabaseModels.leagueController.findAll()) {
					int count = 1;
					System.out.println(count + " - " + league.getLeagueName());
				}
				System.out.print("\nWhich league do you want to select? Please enter the League Number (0=Back to Team Menu): ");
				leagueID = sc.nextInt();
				sc.nextLine();
				
				if (leagueID == 0) {
					return;
				}
				
				Optional<League> byID = DatabaseModels.leagueController.findById(leagueID);
				if (byID.isPresent()) {
					try {
						LeagueModel lm = new LeagueModel(DatabaseModels.getInstance(), byID.get());
						lm.displayLeagueInfo();
						lm.displayLeagueTeams();
						break;
					} catch (Exception e) {
						System.out.println("An error occurred while displaying league information: " + e.getMessage());
					}
				} else {
					System.out.println("\nLeague not found! Please enter a valid ID.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter a numeric value!");
				sc.nextLine();
			}
		}
	}
	
	
	private static void displayLeagueByID() {
		Integer leagueID = null;
		
		while (true) {
			for(League league : DatabaseModels.leagueController.findAll()) {
				int count = 1;
				System.out.println(count + " - " + league.getLeagueName());
			}
			System.out.println("Enter a League Number: (0=Back to Team Menu)");
			
			try {
				leagueID = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Enter a numeric value!");
				sc.nextLine();
				continue;
			}
			
			if (leagueID == 0) {
				return;
			}
			
			Optional<League> byID = DatabaseModels.leagueController.findById(leagueID);
			if (byID.isPresent()) {
				try {
					LeagueModel lm = new LeagueModel(DatabaseModels.getInstance(), byID.get());
					lm.displayLeagueInfo();
					lm.displayLeagueTeams();
					break;
				}
				catch (Exception e) {
					System.out.println("An error occurred while displaying league information: " + e.getMessage());
				}
			}
			else {
				System.out.println("\nLeague not found! Please enter a valid ID.");
			}
		}
	}
	
	
	protected static void displayAllLeagues() {

		List<League> leagues = DatabaseModels.leagueController.findAll();
		if (leagues.isEmpty()){
			System.out.println("\nNo leagues found!");
		}
		else {
			for(League league:leagues){
				LeagueModel lm=new LeagueModel(DatabaseModels.getInstance(), league);
				lm.displayLeagueInfo();
			}
		}

	}
	
	protected static Integer validLeagueIDControl() {
		Integer leagueID=null;
		boolean validLeagueID = false;
		do {
			try {
				for(League league : DatabaseModels.leagueController.findAll()) {
					int count = 1;
					System.out.println(count + " - " + league.getLeagueName());
				}
				System.out.print("Please Enter a League Number: ");
				leagueID = sc.nextInt();
				sc.nextLine();
				Optional<League> league = DatabaseModels.leagueController.findById(leagueID);
				if (league.isPresent()) {
					validLeagueID = true;
				}
				else {
					System.out.println("\nPlease enter a valid League Number.\n");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Enter a numeric value!");
				sc.nextLine();
			}

		} while (!validLeagueID);
		
		return leagueID;
	}
}