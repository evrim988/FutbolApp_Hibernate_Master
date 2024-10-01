package org.example.controller;

import org.example.entities.Transfer;
import org.example.entities.TransferOffer;
import org.example.service.ContractService;
import org.example.service.TransferService;

import java.util.Optional;

public class TransferController {
	private final TransferService transferService;
	
	public TransferController() {
		this.transferService = TransferService.getInstance();
	}
	
	public Transfer save(Transfer transfer) {
		return transferService.save(transfer);
	}
	
	public Transfer update(Transfer transfer) {
		return transferService.update(transfer);
	}
	
	public Optional<Transfer> findById(int id) {
		return transferService.findById(id);
	}
}