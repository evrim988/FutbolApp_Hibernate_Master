package org.example.modules;

import org.example.entities.*;
import org.example.enums.EOfferStatus;
import org.example.enums.EOfferType;
import org.example.models.DatabaseModels;
import org.example.models.PlayerModel;

import java.util.*;

public class ContractModule {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void startContractMenu() {
		int userInput;
		do {
			userInput = contractMenuEntry();
			contractMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int contractMenuEntry() {
		int userInput = -1;
		boolean validInput = false;
		
		while (!validInput) {
			System.out.println("\n---------Contract Menu--------------");
			System.out.println("1- Make a contract offer");
			System.out.println("2- Display Accepted Contract Offers");
			System.out.println("3- Display Your team contracts");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if (userInput >= 0 && userInput <= 4) {
					validInput = true;
				}
				else {
					System.out.println("\nPlease enter a valid option!");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("\nPlease enter a numeric value!");
				sc.next();
			}
		}
		return userInput;
	}
	
	private static void contractMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayAcceptedTransferOffers();
			case 2 -> displayAcceptedContractOffers();
			case 3 -> displayContractByTeam();
			case 0 -> System.out.println("\nReturning to Main Menu...\n");
			default -> System.out.println("Please enter a valid value!");
		}
	}

	private static void displayContractByTeam() {
		List<Contract> allByTeam = DatabaseModels.contractController.findAllByTeam(Menu.loggedManager.getTeam());
		for (Contract contract : allByTeam) {
			System.out.println("\n--------------------------------");
			System.out.println(contract.displayContract());
			System.out.println("\n--------------------------------");
		}
	}

	private static void displayAcceptedTransferOffers() {
		List<TransferOffer> byBuyerTeam =
				DatabaseModels.transferOfferController.findByBuyerTeam(Menu.loggedManager.getTeam());
		
		byBuyerTeam = byBuyerTeam.stream().filter(p -> p.getOfferType().equals(EOfferType.OFFER))
		                         .filter(p -> p.getOfferStatus().equals(EOfferStatus.ACCEPTED))
		                         .filter(p -> p.getState().equals(1)).toList();
		
		for (TransferOffer offer : byBuyerTeam) {
			String displayOffer = offer.displayOffer();
			System.out.println("\n--------------------------------");
			System.out.println(displayOffer);
			System.out.println("\n--------------------------------");
		}
		randomContractAcceptance(makeContractOffer());

	}
	
	private static ContractOffer makeContractOffer() {
		System.out.print("\nWhich player do you want to make contract with? Enter an offer id from above: ");
		Integer offerId = sc.nextInt();
		Optional<TransferOffer> byId = DatabaseModels.transferOfferController.findById(offerId);
		ContractOffer playerContract=null;
		if (byId.isPresent()) {
			TransferOffer transferOffer = byId.get();

			Player player = transferOffer.getPlayer();
			System.out.println("\n--------------------------------");
			System.out.println("Player Name: " + player.getPersonName() + " " + player.getPersonSurname());
			System.out.println("Player Age: " + player.getPersonAge());
			System.out.println("Player Wage: " + player.getPlayerWage());
			System.out.println("\n--------------------------------");

			System.out.print("What is your wage offer: ");
			Double offerPrice = sc.nextDouble();
			System.out.print("What is the contract period? (Max 5 years): ");
			Integer period = sc.nextInt();
			playerContract =
					ContractOffer.builder().wageOffer(offerPrice).contractStartDate(MatchModule.currentDate)
					             .contractEndDate(MatchModule.currentDate.plusYears(period))
					             .team(Menu.loggedManager.getTeam()).player(transferOffer.getPlayer())
					             .offerStatus(EOfferStatus.PENDING).build();
			DatabaseModels.contractOfferController.save(playerContract);
		}
		return playerContract;
	}

	private static boolean randomContractAcceptance(ContractOffer offer) {
		Player offeredPlayer = offer.getPlayer();
		Random random = new Random();
		int randomNumber = random.nextInt(0, 101);


		int playerSkill = offeredPlayer.getPlayerOverallRating(); // 1 - 20 arasında bir değer
		int playerAge = offeredPlayer.getPersonAge(); // Yaş faktörü


		int acceptanceChance = randomNumber;


		if (playerSkill > 10) {
			acceptanceChance -= 10;
		} else if (playerSkill < 6) {
			acceptanceChance += 10;
		}


		if (playerAge < 25) {
			acceptanceChance -= 10;
		} else if (playerAge > 30) {
			acceptanceChance += 10;
		}


		// Teklif değerlendirmesi
		if (offeredPlayer.getPlayerWage() > offer.getWageOffer()) {
			if (acceptanceChance >= 75) {
				System.out.println("Player accepted your offer!");
				offer.setOfferStatus(EOfferStatus.ACCEPTED);
				DatabaseModels.contractOfferController.update(offer);
				return true;
			} else {
				System.out.println("Player rejected your offer!");
				offer.setOfferStatus(EOfferStatus.REJECTED);
				offer.setState(0);
				DatabaseModels.contractOfferController.update(offer);
				return false;
			}
		} else if (offeredPlayer.getPlayerWage() <= offer.getWageOffer()) {
			if (acceptanceChance >= 25) {
				System.out.println("Player accepted your offer!");
				offer.setOfferStatus(EOfferStatus.ACCEPTED);
				DatabaseModels.contractOfferController.update(offer);
				return true;
			} else {
				System.out.println("Player rejected your offer!");
				offer.setOfferStatus(EOfferStatus.REJECTED);
				offer.setState(0);
				DatabaseModels.contractOfferController.update(offer);
				return false;
			}
		}

		return false;
	}


	private static void displayAcceptedContractOffers() {
		boolean validInput;
		List<ContractOffer> acceptedByTeamId =
				DatabaseModels.contractOfferController.findAcceptedByTeamId(Menu.loggedManager.getTeam());
		for (ContractOffer contractOffer: acceptedByTeamId){
			System.out.println("\n--------------------------------");
			System.out.println(contractOffer.displayOffer());
			System.out.println("\n--------------------------------");
		}
		if (acceptedByTeamId.isEmpty()){
			System.out.println("\nYou have no accepted contract offers!");
			return;
		}
		confirmTransfer();
	}
	
	private static void confirmTransfer(){
		System.out.print("These are accepted contract offers. Enter a contract offer id to confirm transfer: ");
		Integer contractOfferId = sc.nextInt();
		Optional<ContractOffer> byId = DatabaseModels.contractOfferController.findById(contractOfferId);
		if(byId.isPresent()){
			ContractOffer contractOffer = byId.get();
			contractAndTransferCreator(contractOffer);
		}
		System.out.println("\nTransfer has been completed. Player is on your team now...");
	}
	
	private static void contractAndTransferCreator(ContractOffer contractOffer) {
		Double wageOffer = contractOffer.getWageOffer();
		Player player = contractOffer.getPlayer();
		Team team = contractOffer.getTeam();
		
		contractOffer.setState(0);
		DatabaseModels.contractOfferController.update(contractOffer);
		
		Contract contract = Contract.builder()
		                            .team(team)
		                            .contractEndDate(contractOffer.getContractEndDate())
		                            .contractStartDate(contractOffer.getContractStartDate())
		                            .player(player)
		                            .wage(wageOffer)
		                            .build();
		
		Optional<TransferOffer> transferOffer =
				DatabaseModels.transferOfferController.findAcceptedOfferByBuyerTeamIdAndPlayerId(team.getId(), player.getId());
		
		if (transferOffer.isPresent()) {
			TransferOffer toffer = transferOffer.get();
			Transfer transfer = Transfer.builder()
			                            .contract(contract)
			                            .transferPrice(toffer.getOfferPrice())
			                            .transferDate(MatchModule.currentDate)
			                            .player(player)
			                            .buyerClub(team)
			                            .sellerClub(toffer.getOwnerClub())
			                            .build();
			contract.setTransfer(transfer);
			DatabaseModels.transferController.save(transfer);
			setPlayersNewTeamAndTeamBudget(transfer);
		}
	}
	
	public static void setPlayersNewTeamAndTeamBudget(Transfer transfer) {
		Player player = transfer.getPlayer();
		Team buyerTeam = transfer.getBuyerClub();
		Team sellerTeam = transfer.getSellerClub();
		Contract contract = transfer.getContract();
		
		player.setTeam(buyerTeam);
		player.setPlayerWage(contract.getWage());
		DatabaseModels.playerController.update(player);
		
		buyerTeam.setTransferBudget(buyerTeam.getTransferBudget()-transfer.getTransferPrice());
		buyerTeam.setWageBudget(buyerTeam.getWageBudget()-contract.getWage());
		DatabaseModels.teamController.update(buyerTeam);
		
		sellerTeam.setTransferBudget(sellerTeam.getTransferBudget()+transfer.getTransferPrice());
		sellerTeam.setWageBudget(sellerTeam.getWageBudget()+contract.getWage());
		DatabaseModels.teamController.update(sellerTeam);
	}
	
}