package org.example.repository;

import org.example.entities.ContractOffer;

public class ContractOfferRepository extends RepositoryManager<ContractOffer, Integer> {

    public ContractOfferRepository() {
        super(ContractOffer.class);
    }
}
