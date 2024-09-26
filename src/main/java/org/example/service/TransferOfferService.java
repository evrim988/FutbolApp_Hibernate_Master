package org.example.service;

import org.example.entities.TransferOffer;
import org.example.repository.ICRUD;
import org.example.repository.LeagueRepository;
import org.example.repository.TransferOfferRepository;

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
}
