package org.example.controller;

import org.example.entities.Team;
import org.example.entities.TransferOffer;
import org.example.service.TransferOfferService;

import java.util.List;
import java.util.Optional;

public class TransferOfferController {

    private TransferOfferService transferOfferService;

    public TransferOfferController() {
        this.transferOfferService = TransferOfferService.getInstance();
    }

    public TransferOffer save(TransferOffer transferOffer) {
        return transferOfferService.save(transferOffer);
    }

    public TransferOffer update(TransferOffer transferOffer) {
        return transferOfferService.update(transferOffer);
    }

    public Optional<TransferOffer> findById(int id) {
        return transferOfferService.findById(id);
    }

    public List<TransferOffer> findByBuyerTeam(Team team) {
        return transferOfferService.findByBuyerTeam(team);
    }

    public List<TransferOffer> findByOwnerTeam(Team team) {
        return transferOfferService.findByOwnerTeam(team);
    }
    
    public Optional<TransferOffer> findAcceptedOfferByBuyerTeamIdAndPlayerId(Integer teamId, Integer playerId) {
        return transferOfferService.findAcceptedOfferByBuyerTeamIdAndPlayerId(teamId, playerId);
    }
}