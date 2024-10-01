package org.example.modules;

import org.example.entities.Player;
import org.example.entities.Team;
import org.example.entities.Transfer;
import org.example.entities.TransferOffer;
import org.example.entities.attributes.MentalAttributes;
import org.example.entities.attributes.PhysicalAttributes;
import org.example.entities.attributes.TechnicalAttributes;
import org.example.enums.EOfferStatus;
import org.example.enums.EOfferType;
import org.example.enums.EPosition;
import org.example.models.DatabaseModels;
import org.example.models.PlayerModel;

import javax.xml.crypto.Data;
import java.util.*;

public class TransferModule {
    static Scanner sc = new Scanner(System.in);
    static  List<Integer> idList = new ArrayList<>();

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
            System.out.println("\n---------Transfer Menu--------------");
            System.out.println("1-Make an offer");
            System.out.println("2-Incoming offers");
            System.out.println("3-Your Offers");
            System.out.println("4-Counter Offers");
            System.out.println("5-Top transfers");
            System.out.println("6-Contract Menu");
            System.out.println("7-Your Transfers");
            System.out.println("0-Main Menu");
            System.out.print("Selection: ");
            try {
                userInput = sc.nextInt();
                sc.nextLine();
                if(userInput >= 0 && userInput <= 7) {
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
            case 1 -> makeOffer();
            case 2 -> displayIncomingOffers();
            case 3 -> displayYourOffers();
            case 4 -> displayCounterOffers();
            case 5 -> displayTopTransfer();
            case 6 -> ContractModule.startContractMenu();
            case 7 -> displayTransfersByTeam();
            case 0 -> System.out.println("\nReturning to Main Menu...\n");
            default-> System.out.println("Please enter a valid value!");
        }
    }

    private static void displayTransfersByTeam() {
        List<Transfer> allTransferByTeam = DatabaseModels.transferController.findAllTransferByTeam(Menu.loggedManager.getTeam());
        for (Transfer transfer : allTransferByTeam) {
            System.out.println("\n--------------------------------------");
            System.out.println(transfer.displayTransfer());
            System.out.println("\n--------------------------------------");
        }
    }

    private static void displayTopTransfer() {
        List<Transfer> allTopTransfers = DatabaseModels.transferController.findAllTopTransfers();
        for (Transfer transfer : allTopTransfers) {
            System.out.println("\n--------------------------------------");
            System.out.println(transfer.displayTransfer());
            System.out.println("\n--------------------------------------");
        }
    }

    private static void displayCounterOffers() {
        List<TransferOffer> byBuyerTeam = DatabaseModels.transferOfferController.findByBuyerTeam(Menu.loggedManager.getTeam());

        byBuyerTeam = byBuyerTeam.stream().filter(p -> p.getOfferType().equals(EOfferType.COUNTER_OFFER)).toList();

        for (TransferOffer offer : byBuyerTeam) {
            String displayOffer = offer.displayOffer();
            System.out.println("\n--------------------------------");
            System.out.println(displayOffer);
            System.out.println("\n--------------------------------");

        }
        selectionCounterOfferMenu(counterOfferMenu());
    }

    private static Integer counterOfferMenu(){
        int userInput = -1;
        boolean validInput = false;

        while(!validInput) {
            System.out.println("\n---------Counter Offer Menu--------------");
            System.out.println("1-Accept counter offer by id ");
            System.out.println("2-Decline counter offer by id ");
            System.out.println("0-Transfer menu ");
            System.out.print("Selection: ");
            try {
                userInput = sc.nextInt();
                sc.nextLine();
                if(userInput >= 0 && userInput <= 3) {
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

    private static void selectionCounterOfferMenu(Integer userInput) {
        switch (userInput) {
            case 1 -> acceptCounterOffer();
            case 2 -> rejectCounterOffer();
            case 0 -> System.out.println("\nReturning to Transfer Menu...\n");
            default-> System.out.println("Please enter a valid value!");
        }
    }

    private static void acceptCounterOffer() {
        System.out.print("\nPlease enter an id to accept counter offer: ");
        Integer counterOfferId = sc.nextInt();
        Optional<TransferOffer> byId = DatabaseModels.transferOfferController.findById(counterOfferId);
        if (byId.isPresent()) {
            TransferOffer transferOffer = byId.get();
            transferOffer.setOfferStatus(EOfferStatus.ACCEPTED);
            transferOffer.setState(0);
            DatabaseModels.transferOfferController.update(transferOffer);
            TransferOffer transferOffer2 = TransferOffer.builder()
                    .offerDate(MatchModule.currentDate)
                    .offerPrice(transferOffer.getOfferPrice())
                    .offerType(EOfferType.OFFER)
                    .offerStatus(EOfferStatus.PENDING)
                    .buyerClub(transferOffer.getBuyerClub())
                    .ownerClub(transferOffer.getOwnerClub())
                    .player(transferOffer.getPlayer())
                    .build();
            DatabaseModels.transferOfferController.save(transferOffer2);
        }
    }

    private static void rejectCounterOffer() {
        System.out.print("\nPlease enter an id to reject counter offer: ");
        Integer counterOfferId = sc.nextInt();
        Optional<TransferOffer> byId = DatabaseModels.transferOfferController.findById(counterOfferId);
        if (byId.isPresent()) {
            TransferOffer transferOffer = byId.get();
            transferOffer.setOfferStatus(EOfferStatus.REJECTED);
            DatabaseModels.transferOfferController.update(transferOffer);
        }
    }

    private static void displayIncomingOffers() {
        List<TransferOffer> byOwnerTeam = DatabaseModels.transferOfferController.findByOwnerTeam(Menu.loggedManager.getTeam());

        List<TransferOffer> passivebyOwnerTeam = byOwnerTeam.stream()
                .filter(p -> p.getOfferType().equals(EOfferType.OFFER))
                .filter(p -> p.getState().equals(0))
                .toList();

        List<TransferOffer> activebyOwnerTeam = byOwnerTeam.stream()
                .filter(p -> p.getOfferType().equals(EOfferType.OFFER))
                .filter(p -> p.getState().equals(1))
                .toList();

        for (TransferOffer offer : passivebyOwnerTeam) {
            String displayOffer = offer.displayOffer();
            System.out.println("\n--------------Inactive Offer List------------------");
            System.out.println(displayOffer);
            Integer id = offer.getId();
            idList.add(id);

            System.out.println("\n--------------------------------");

        }

        for (TransferOffer offer : activebyOwnerTeam) {
            String displayOffer = offer.displayOffer();
            System.out.println("\n--------------Active Offer List------------------");
            System.out.println(displayOffer);
            System.out.println("\n--------------------------------");
        }
        transferIncomingMenuSelection(incomingOfferMenu());
    }

    private static Integer incomingOfferMenu() {
        int userInput=-1;
        boolean validInput = false;

        while(!validInput) {
            System.out.println("\n---------Incoming Offer Menu--------------");
            System.out.println("1-Accept an offer by id ");
            System.out.println("2-Reject an offer by id ");
            System.out.println("3-Make an counter offer by offer id ");
            System.out.println("0-Transfer Menu");
            System.out.print("Selection: ");
            try {
                userInput = sc.nextInt();
                sc.nextLine();
                if(userInput >= 0 && userInput <= 3) {
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

    private static void transferIncomingMenuSelection(Integer userInput) {
        switch (userInput) {
            case 1 -> acceptOffer();
            case 2 -> rejectOffer();
            case 3 -> makeCounterOffer();
            case 0 -> System.out.println("\nReturning to Main Menu...\n");
            default-> System.out.println("Please enter a valid value!");
        }
    }

    private static void makeCounterOffer() {
        System.out.print("Which offer would you like to make a counter offer? Enter an id: ");
        Integer offerId = sc.nextInt();
        Optional<TransferOffer> byId = DatabaseModels.transferOfferController.findById(offerId);
        if(byId.isPresent()) {
            TransferOffer transferOffer = byId.get();
            transferOffer.setState(0);
            transferOffer.setOfferStatus(EOfferStatus.REJECTED);
            DatabaseModels.transferOfferController.update(transferOffer);
            System.out.print("\nHow much do you ask for: ");
            Double counterOffer = sc.nextDouble();
            TransferOffer transferOffer2 = TransferOffer.builder()
                    .offerDate(MatchModule.currentDate)
                    .offerPrice(counterOffer)
                    .offerType(EOfferType.COUNTER_OFFER)
                    .offerStatus(EOfferStatus.PENDING)
                    .buyerClub(transferOffer.getBuyerClub())
                    .ownerClub(transferOffer.getOwnerClub())
                    .player(transferOffer.getPlayer())
                    .build();
            DatabaseModels.transferOfferController.save(transferOffer2);
            System.out.println(transferOffer2.displayOffer());
        }
    }

    private static void rejectOffer() {
        System.out.print("Which offer would you like to reject? Enter an id: ");
        Integer offerId = sc.nextInt();
        Optional<TransferOffer> byId = DatabaseModels.transferOfferController.findById(offerId);
        if(byId.isPresent()) {
            TransferOffer transferOffer = byId.get();
            transferOffer.setState(0);
            transferOffer.setOfferStatus(EOfferStatus.REJECTED);
            DatabaseModels.transferOfferController.update(transferOffer);
        }
        else {
            System.out.println("\nPlease enter a valid id!");
        }
    }

    private static void acceptOffer() {
        int validInput = -1;
        boolean isValid = false;
        while (!isValid){
            Integer offerId;
            System.out.print("Which offer would you like to accept? Enter an id: ");
            offerId = sc.nextInt();
            for(Integer id: idList) {
                if (id == offerId) {
                    while (true){
                        System.out.println("\nYou can not enter inactive offer id. Try again!");
                        offerId = sc.nextInt();
                    }

                }
            }
            Optional<TransferOffer> byId = DatabaseModels.transferOfferController.findById(offerId);
            if(byId.isPresent()) {
                TransferOffer transferOffer = byId.get();
                transferOffer.setState(1);
                transferOffer.setOfferStatus(EOfferStatus.ACCEPTED);
                DatabaseModels.transferOfferController.update(transferOffer);
                isValid = true;
            }
            else {
                System.out.println("\nPlease enter a valid id!");
            }
        }

    }



    private static void displayYourOffers() {
        List<TransferOffer> byBuyerTeam = DatabaseModels.transferOfferController.findByBuyerTeam(Menu.loggedManager.getTeam());

       byBuyerTeam = byBuyerTeam.stream().filter(p -> p.getOfferType().equals(EOfferType.OFFER)).toList();

        for (TransferOffer offer : byBuyerTeam) {
            String displayOffer = offer.displayOffer();
            System.out.println("\n--------------------------------");
            System.out.println(displayOffer);
            System.out.println("\n--------------------------------");
        }
    }

    private static void makeOffer() {

        PlayerModule.displayPlayersByTeamName();
        System.out.print("Which player do you want to transfer? Enter an id: ");
        Integer playerId = sc.nextInt();
        sc.nextLine();
        Optional<Player> playerById = DatabaseModels.playerController.findById(playerId);
        if(playerById.isPresent()) {
            Player player = playerById.get();
            Integer id = player.getId();
            String personName = player.getPersonName();
            String personSurname = player.getPersonSurname();
            Integer personAge = player.getPersonAge();
            String personNationality = player.getPersonNationality();
            Team team = player.getTeam();
            Double wage = player.getPlayerWage();
            Double playerValue = player.getPlayerValue();
            EPosition playersPosition = player.getPlayersPosition();
            TechnicalAttributes playerTechnicalAttributes = player.getPlayerTechnicalAttributes();
            MentalAttributes playerMentalAttributes = player.getPlayerMentalAttributes();
            PhysicalAttributes playerPhysicalAttributes = player.getPlayerPhysicalAttributes();
            PlayerModel playerModel = PlayerModel.builder()
                    .playerID(id)
                    .playerName(personName)
                    .playerSurname(personSurname)
                    .playerAge(personAge)
                    .playerNationality(personNationality)
                    .playersTeam(team)
                    .playerWage(wage)
                    .playerValue(playerValue)
                    .playersPosition(playersPosition)
                    .playerTechnicalAttributes(playerTechnicalAttributes)
                    .playerMentalAttributes(playerMentalAttributes)
                    .playerPhysicalAttributes(playerPhysicalAttributes)
                    .build();
            playerModel.displayPlayerInfo();
        }
        System.out.print("\nDo you want to make an offer (y/n): ");
        String answer = sc.nextLine();
        if(answer.equalsIgnoreCase("y")) {
            System.out.print("\nHow much do you want to offer: ");
            Double offerPrice = sc.nextDouble();
            sc.nextLine();
            TransferOffer transferOffer = TransferOffer.builder()
                    .offerPrice(offerPrice)
                    .offerDate(MatchModule.currentDate)
                    .offerType(EOfferType.OFFER)
                    .offerStatus(EOfferStatus.PENDING)
                    .player(playerById.get())
                    .buyerClub(Menu.loggedManager.getTeam())
                    .ownerClub(playerById.get().getTeam())
                    .build();
            DatabaseModels.transferOfferController.save(transferOffer);
            String displayOffer = transferOffer.displayOffer();
            System.out.println(displayOffer);
        }
    }
}
