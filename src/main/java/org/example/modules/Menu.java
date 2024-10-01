package org.example.modules;



import org.example.entities.Manager;
import org.example.entities.TransferOffer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	private static Scanner sc = new Scanner(System.in);
	public static Manager loggedManager = null;
	
	
	public static void startMenu() {
		int userInput;
		do {
			userInput = menuEntry();
			menuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int menuEntry() {
		int userInput = -1;
		boolean validInput = false;
		
		while (!validInput) {
			if (loggedManager == null){
				System.out.println("1-Log In");
			}
			System.out.println("2-League Menu");
			System.out.println("3-Team Menu");
			System.out.println("4-Player Menu");
			System.out.println("5-Fixture Menu");
			System.out.println("6-Match Menu");
			System.out.println("7-MyTeam");
			System.out.println("8-Transfer Menu");
			if (loggedManager != null)
				System.out.println("9-Log Out");
			System.out.println("0-Exit");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				
				if (loggedManager == null) {
					if (userInput == 1 || userInput == 0 || userInput == 8) {
						validInput = true;
					} else if (userInput == 2 || userInput == 3|| userInput == 4 || userInput==5|| userInput == 6|| userInput == 7) {
						System.out.println("\nYou need to log in first!");
					} else {
						System.out.println("\nPlease enter a valid value!\n");
					}
				} else {
					if ( userInput == 2 || userInput == 7 || userInput == 3 || userInput == 9 || userInput == 0 || userInput == 4 || userInput==5|| userInput == 6 || userInput == 8) {
						validInput = true;
					} else {
						System.out.println("\nPlease enter a valid value!");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter a numeric value!");
				sc.next();
			}
		}
		return userInput;
	}
	
	private static void menuSelection(int userInput) {
		switch (userInput) {
			case 1 -> loggedManager=LogInModule.managerSelection();
			case 2 -> LeagueModule.startLeagueMenu();
			case 3 -> TeamModule.startTeamMenu();
			case 4 -> PlayerModule.startPlayerMenu();
			case 5 -> FixtureModule.startFixtureMenu();
			case 6 -> MatchModule.startMatchMenu();
			case 7 -> MyTeamModule.startMyTeamMenu();
			case 8 -> TransferModule.startTransferMenu();
//			case 9 -> loggedManager=LogInModule.managerLogOut();
			case 0 -> quit();
			default-> System.out.println("\nPlease enter a valid value!");
		}
	}
	
	public static void quit() {
		/*System.out.println("Do you want to save your changes? (y/n)");
		String choice = sc.next();
		sc.nextLine();
		if (choice.equalsIgnoreCase("y")) {
			//TODO: herşeyi kaydet
			System.out.println("Changes saved successfully!");
		}*/
		System.out.println("\nHave a nice day!");
	}
}