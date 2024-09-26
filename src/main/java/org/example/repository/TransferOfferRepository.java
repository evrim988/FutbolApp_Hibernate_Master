package org.example.repository;

import org.example.entities.TransferOffer;

public class TransferOfferRepository extends RepositoryManager<TransferOffer, Integer> {

    public TransferOfferRepository() {
        super(TransferOffer.class);
    }
}
