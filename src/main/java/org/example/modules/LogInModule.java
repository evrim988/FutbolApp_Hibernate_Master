package org.example.modules;



import org.example.entities.League;
import org.example.entities.Manager;
import org.example.entities.Team;
import org.example.models.DatabaseModels;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class LogInModule {
	static Scanner sc = new Scanner(System.in);
	static Manager loggedManager = null;

	public static Manager managerSelection() {
		System.out.println("\nWelcome to the Football Manager App!");
		System.out.println("\n Do you want to continue as a existing manager or create a new one? (1/2)");
		int choice = sc.nextInt();
		sc.nextLine();
		if(choice == 2) {
			register();
			return logIn();
		}
		System.out.println("\nPlease choose a league first!");
		System.out.println("\n---------------Available Leagues------------------");
		LeagueModule.displayAllLeagues();

		Integer leagueID = null;
		boolean validLeagueID = false;

		do {
			try {

				System.out.print("Please Enter a League Number: ");
				leagueID = sc.nextInt();
				sc.nextLine();
				Optional<League> league = DatabaseModels.leagueController.findById(leagueID);
				if (league.isPresent()) {
					validLeagueID = true;
				} else {
					System.out.println("Please enter a valid League ID.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter a numeric value!");
				sc.nextLine();
			}
		} while (!validLeagueID);

		System.out.println("\n---------------Available Managers in The League------------------");
		List<Manager> all = DatabaseModels.managerController.findAllByLeague(leagueID);

		all.forEach(manager -> {
			System.out.println(manager.getId() + " - " +  manager.getPersonName() +  " " + manager.getPersonSurname() + " " + manager.getTeam().getTeamName());

		});

		boolean validInput = false;
		do {
			System.out.print("Enter a Manager ID to see login information: (0 to Menu): ");
			Optional<Integer> managerID = Optional.empty();

			try {
				managerID = Optional.of(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please enter a numeric value!");
				sc.nextLine();
				continue;
			}

			sc.nextLine();

			if (managerID.isPresent()) {
				int id = managerID.get();
				if (id == 0) {
					return null;
				}
				if (id > 0 && id <= DatabaseModels.managerController.findAll().size()) {
					Optional<Manager> byID = DatabaseModels.managerController.findById(id);
					if (byID.isPresent()) {
						System.out.println("\n------------------------------------------------");
						System.out.println("Username: " + byID.get().getManagerUserName() + ", Password: " + byID.get().getManagerPassword());
						validInput = true;
						System.out.println("------------------------------------------------");
					} else {
						System.out.println("Manager not found! Please enter a valid ID.");
					}
				} else {
					System.out.println("Invalid ID. Please enter a value within the valid range.");
				}
			}
		} while (!validInput);

		return logIn();
	}

	private static Manager logIn() {
		do {
			System.out.print("\nEnter your Username: ");
			String username = sc.nextLine();
			System.out.print("Enter your Password: ");
			String password = sc.nextLine();

			Optional<Manager> byUsernameAndPassword = DatabaseModels.managerController.findByUserNameAndPassword(username, password);
			if (byUsernameAndPassword.isPresent()) {
				System.out.println("Login Successful!");
				System.out.println("\nYou are playing as: " + byUsernameAndPassword.get().getPersonName() + " " + byUsernameAndPassword.get().getPersonSurname() + "\n");
				loggedManager = byUsernameAndPassword.get();
				return loggedManager;
			} else {
				System.out.println("\nInvalid credentials. Please try again.");
			}
		} while (true);
	}

	public static Manager managerLogOut(){
		loggedManager=null;
		System.out.println("You are logged out!");
		return loggedManager;
	}

	public static Manager register(){
		System.out.println("\n-----------Register------------------");
		System.out.print("Enter your name: ");
		String name = sc.nextLine();
		System.out.print("Enter your surname: ");
		String surname = sc.nextLine();
		System.out.print("Enter your age: ");
		Integer age = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter your nationality: ");
		String nationality = sc.nextLine();
		System.out.print("Enter your username: ");
		String username = sc.nextLine();
		boolean b = DatabaseModels.managerController.existsByUsername(username);
		while(b){
			System.out.print("Username already exists! Please enter a different one: ");
			username = sc.nextLine();
			b = DatabaseModels.managerController.existsByUsername(username);
		}
		System.out.print("Enter your password: ");
		String password = sc.nextLine();
		System.out.println("-----------------Available Teams----------------------- ");
		List<Team> teamList = DatabaseModels.teamController.findAllByLeague(1);
		teamList.forEach(team -> {
			System.out.println(team.getId() + " - " + team.getTeamName());
		});
		System.out.print("Which team would you like to manage? Enter an id: ");
		Integer teamID = sc.nextInt();
		sc.nextLine();
		DatabaseModels.teamController.findById(teamID).ifPresentOrElse(team -> {
			System.out.println(team.getId() + " - " + team.getTeamName());
		}, () -> System.out.println("Team not found!"));
		Optional<Team> byId = DatabaseModels.teamController.findById(teamID);
		while(byId.isEmpty()){
			System.out.println("Please enter a valid team ID!");
			teamID = sc.nextInt();
			sc.nextLine();
		}
		if(byId.isPresent()){
			Optional<Manager> optionalManager = DatabaseModels.managerController.findByTeamId(byId.get().getId());
			if(optionalManager.isPresent()){
				Manager oldManager = optionalManager.get();
				oldManager.setTeam(null);
				DatabaseModels.managerController.update(oldManager);
			}
			Manager manager = Manager.builder().personName(name).personSurname(surname).personAge(age).personNationality(nationality)
					.managerUserName(username).managerPassword(password).team(byId.get()).build();
			DatabaseModels.managerController.save(manager);
			System.out.println("Registration successful!");
			return manager;
		}
		return null;
	}

}