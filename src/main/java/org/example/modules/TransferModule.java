package org.example.modules;

import org.example.entities.Player;
import org.example.entities.Team;
import org.example.entities.TransferOffer;
import org.example.entities.attributes.MentalAttributes;
import org.example.entities.attributes.PhysicalAttributes;
import org.example.entities.attributes.TechnicalAttributes;
import org.example.enums.EOfferStatus;
import org.example.enums.EOfferType;
import org.example.enums.EPosition;
import org.example.models.DatabaseModels;
import org.example.models.PlayerModel;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class TransferModule {
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
            System.out.println("\n---------Transfer Menu--------------");
            System.out.println("1-Make an offer");
            System.out.println("2-Incoming offers");
            System.out.println("3-Top transfers");
            System.out.println("0-Main Menu");
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

    private static void transferMenuSelection(int userInput) {
        switch (userInput) {
            case 1 -> makeOffer();

            case 0 -> System.out.println("\nReturning to Main Menu...\n");
            default-> System.out.println("Please enter a valid value!");
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

        }
    }
}
