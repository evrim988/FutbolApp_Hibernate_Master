package org.example.modules;



import org.example.entities.League;
import org.example.entities.Team;
import org.example.models.DatabaseModels;
import org.example.models.FixtureModel;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FixtureModule {
	static Scanner sc = new Scanner(System.in);
	
	public static void startFixtureMenu() {
		int userInput;
		do {
			userInput = fixtureMenuEntry();
			fixtureMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int fixtureMenuEntry() {
		int userInput=-1;
		boolean validInput = false;
		
		while(!validInput) {
			System.out.println("\n---------Fixture Menu--------------");
			System.out.println("1-Fixture By League ID");
			System.out.println("2-Fixture By Team Name");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if(userInput >= 0 && userInput <= 2) {
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
	private static void fixtureMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayFixtureByLeague();
			case 2 -> displayTeamFixtureByName();
			case 0 -> System.out.println("\nReturning to FootballApp.Main Menu...\n");
			default-> System.out.println("Please enter a valid value!");
		}
	}

	private static void displayTeamFixtureByName() {
		System.out.println("Enter a team name:");
		String name=sc.nextLine();
		List<Team> teamList = DatabaseModels.teamController.findByFieldNameAndValue("teamName",name);
		if(teamList.isEmpty()) {
			System.out.println("\nTeam not found!");
		}
		else {
			Team firstTeam = teamList.getFirst();
			FixtureModel fixtureModel = new FixtureModel(DatabaseModels.getInstance(),firstTeam.getLeague());
			fixtureModel.displayTeamFixture(name);
		}

	}
	
	private static void displayFixtureByLeague() {
		Integer leagueId = null;
		boolean validInput = false;
		
		do {
			try {
				for(League league : DatabaseModels.leagueController.findAll()) {
					int count = 1;
					System.out.println(count + " - " + league.getLeagueName());
				}
				System.out.println("Enter a League Number: ");
				leagueId = sc.nextInt();
				sc.nextLine();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a numeric value!");
				sc.nextLine();
			}

		} while (!validInput);
		
		if (leagueId != null) {
			Optional<League> byID = DatabaseModels.leagueController.findById(leagueId);
			if (byID.isPresent()) {
				FixtureModel fm = new FixtureModel(DatabaseModels.getInstance(), byID.get());
				fm.displayLeagueFixture();
			}
			else {
				System.out.println("Please enter a valid League ID!");
			}
		}
	}
	
}