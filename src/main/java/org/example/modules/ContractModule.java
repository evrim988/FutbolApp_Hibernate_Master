package org.example.modules;

import org.example.entities.*;
import org.example.enums.EOfferStatus;
import org.example.enums.EOfferType;
import org.example.models.DatabaseModels;

import java.util.*;

public class ContractModule {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void startTransferMenu() {
		int userInput;
		do {
			userInput = transferMenuEntry();
			transferMenuSelection(userInput);
		} while (userInput != 0);
	}
	
	private static int transferMenuEntry() {
		int userInput = -1;
		boolean validInput = false;
		
		while (!validInput) {
			System.out.println("\n---------Contract Menu--------------");
			System.out.println("1- Make a contract offer");
			System.out.println("0-Main Menu");
			System.out.print("Selection: ");
			try {
				userInput = sc.nextInt();
				sc.nextLine();
				if (userInput >= 0 && userInput <= 5) {
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
	
	private static void transferMenuSelection(int userInput) {
		switch (userInput) {
			case 1 -> displayAcceptedTransferOffers();
			case 0 -> System.out.println("\nReturning to Main Menu...\n");
			default -> System.out.println("Please enter a valid value!");
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
		
	}
	
	private static ContractOffer makeContractOffer() {
		System.out.print("\nWhich player do you want to make contract with? Enter an offer id from above: ");
		Integer offerId = sc.nextInt();
		Optional<TransferOffer> byId = DatabaseModels.transferOfferController.findById(offerId);
		ContractOffer playerContract=null;
		if (byId.isPresent()) {
			TransferOffer transferOffer = byId.get();
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
		Random random=new Random();
		int randomNumber=random.nextInt(1,11);
		if(offeredPlayer.getPlayerWage()> offer.getWageOffer()){
			if(randomNumber>=9){
				System.out.println("Player accepted your offer!");
				offer.setOfferStatus(EOfferStatus.ACCEPTED);
				DatabaseModels.contractOfferController.update(offer);
				return true;
			}
			else{
				System.out.println("Player rejected your offer!");
                offer.setOfferStatus(EOfferStatus.REJECTED);
                offer.setState(0);
                DatabaseModels.contractOfferController.update(offer);
				return false;
			}
		}
		if (offeredPlayer.getPlayerWage()<= offer.getWageOffer()){
			if(randomNumber>=3){
				System.out.println("Player accepted your offer!");
                offer.setOfferStatus(EOfferStatus.ACCEPTED);
                DatabaseModels.contractOfferController.update(offer);
				return true;
			}
			else{
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
		List<ContractOffer> acceptedByTeamId =
				DatabaseModels.contractOfferController.findAcceptedByTeamId(Menu.loggedManager.getTeam().getId());
		for (ContractOffer contractOffer: acceptedByTeamId){
			System.out.println("\n--------------------------------");
			contractOffer.displayOffer();
			System.out.println("\n--------------------------------");
		}
	}
	
	private static void confirmTransfer(){
		System.out.println("These are accepted contract offers. Enter a contract offer id to confirm transfer:");
		Integer contractOfferId = sc.nextInt();
		Optional<ContractOffer> byId = DatabaseModels.contractOfferController.findById(contractOfferId);
		if(byId.isPresent()){
			ContractOffer contractOffer = byId.get();
			
		}
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
			DatabaseModels.transferController.save(transfer);
			contract.setTransfer(transfer);
		}
        DatabaseModels.contractController.save(contract);
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