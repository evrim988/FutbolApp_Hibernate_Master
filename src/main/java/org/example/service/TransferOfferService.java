package org.example.service;

import org.example.entities.Team;
import org.example.entities.TransferOffer;
import org.example.repository.ICRUD;
import org.example.repository.LeagueRepository;
import org.example.repository.TransferOfferRepository;

import java.util.List;
import java.util.Optional;

public class TransferOfferService extends ServiceManager<TransferOffer, Integer> {
    private final TransferOfferRepository transferOfferRepository;

    private static TransferOfferService instance;

    private TransferOfferService() {
        this(new TransferOfferRepository());
    }

    private TransferOfferService(TransferOfferRepository repository) {
        super(repository);
        this.transferOfferRepository = repository;
    }

    public static TransferOfferService getInstance() {
        if (instance == null) {
            instance = new TransferOfferService();
        }
        return instance;
    }

    public List<TransferOffer> findByBuyerTeam(Team team) {
        return transferOfferRepository.findByBuyerTeam(team);
    }

    public List<TransferOffer> findByOwnerTeam(Team team) {
        return transferOfferRepository.findByOwnerTeam(team);
    }
    
    public Optional<TransferOffer> findAcceptedOfferByBuyerTeamIdAndPlayerId(Integer teamId, Integer playerId) {
        return transferOfferRepository.findAcceptedOffersByBuyerTeamIdAndPlayerId(teamId, playerId);
    }
}