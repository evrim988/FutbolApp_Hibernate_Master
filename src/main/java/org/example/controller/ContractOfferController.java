package org.example.controller;

import org.example.entities.ContractOffer;
import org.example.service.ContractOfferService;

public class ContractOfferController {

    private final ContractOfferService contractOfferService;

    public ContractOfferController() {
        this.contractOfferService = ContractOfferService.getInstance();
    }

    public ContractOffer save(ContractOffer contractOffer) {
        return contractOfferService.save(contractOffer);
    }
}
