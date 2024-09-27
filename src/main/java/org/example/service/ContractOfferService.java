package org.example.service;

import org.example.entities.ContractOffer;
import org.example.repository.ContractOfferRepository;
import org.example.repository.RepositoryManager;

public class ContractOfferService extends ServiceManager<ContractOffer, Integer> {
    private final ContractOfferRepository repository;

    private static ContractOfferService instance;

    private ContractOfferService() {
        this(new ContractOfferRepository());
    }

    private ContractOfferService(ContractOfferRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public static ContractOfferService getInstance() {
        if (instance == null) {
            instance = new ContractOfferService();
        }
        return instance;
    }
}
