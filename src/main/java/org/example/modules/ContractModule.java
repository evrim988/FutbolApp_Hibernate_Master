package org.example.modules;

import org.example.entities.ContractOffer;
import org.example.entities.TransferOffer;
import org.example.enums.EOfferStatus;
import org.example.enums.EOfferType;
import org.example.models.DatabaseModels;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
        int userInput=-1;
        boolean validInput = false;

        while(!validInput) {
            System.out.println("\n---------Contract Menu--------------");
            System.out.println("1- Make a contract offer");
            System.out.println("0-Main Menu");
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

    private static void transferMenuSelection(int userInput) {
        switch (userInput) {
            case 1 -> displayAcceptedOffers();
            case 0 -> System.out.println("\nReturning to Main Menu...\n");
            default-> System.out.println("Please enter a valid value!");
        }
    }

    private static void displayAcceptedOffers() {
        List<TransferOffer> byBuyerTeam = DatabaseModels.transferOfferController.findByBuyerTeam(Menu.loggedManager.getTeam());

        byBuyerTeam = byBuyerTeam.stream()
                .filter(p -> p.getOfferType().equals(EOfferType.OFFER))
                .filter(p -> p.getOfferStatus().equals(EOfferStatus.ACCEPTED))
                .filter(p -> p.getState().equals(1))
                .toList();

        for (TransferOffer offer : byBuyerTeam) {
            String displayOffer = offer.displayOffer();
            System.out.println("\n--------------------------------");
            System.out.println(displayOffer);
            System.out.println("\n--------------------------------");
        }

    }

    private static void makeContractOffer() {
        System.out.print("\nWhich player do you want to make contract with? Enter an id: ");
        Integer offerId = sc.nextInt();
        Optional<TransferOffer> byId = DatabaseModels.transferOfferController.findById(offerId);
        if (byId.isPresent()) {
            TransferOffer transferOffer = byId.get();
            System.out.print("What is your wage offer: ");
            Double offerPrice = sc.nextDouble();
            System.out.print("What is the contract period? (Max 5 years): ");
            Integer period = sc.nextInt();
            ContractOffer playerContract = ContractOffer.builder()
                    .wageOffer(offerPrice)
                    .contractStartDate(MatchModule.currentDate)
                    .contractEndDate(MatchModule.currentDate.plusYears(period))
                    .team(Menu.loggedManager.getTeam())
                    .player(transferOffer.getPlayer())
                    .offerStatus(EOfferStatus.PENDING)
                    .build();
            DatabaseModels.contractOfferController.save(playerContract);
        }
    }

    private static void sendOfferToPlayer(ContractOffer offer) {

    }


}
