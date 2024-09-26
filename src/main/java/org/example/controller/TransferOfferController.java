package org.example.controller;

import org.example.entities.TransferOffer;
import org.example.service.TransferOfferService;

public class TransferOfferController {

    private TransferOfferService transferOfferService;

    public TransferOfferController() {
        this.transferOfferService = TransferOfferService.getInstance();
    }

    public TransferOffer save(TransferOffer transferOffer) {
        return transferOfferService.save(transferOffer);
    }
}
