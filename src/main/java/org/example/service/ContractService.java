package org.example.service;

import org.example.entities.Contract;
import org.example.entities.Transfer;
import org.example.repository.ContractRepository;
import org.example.repository.TransferRepository;

public class ContractService extends ServiceManager<Contract, Integer>  {
	
	private final ContractRepository contractRepository;
	
	private static ContractService instance;
	
	private ContractService() {
		this(new ContractRepository());
	}
	
	private ContractService(ContractRepository repository) {
		super(repository);
		this.contractRepository = repository;
	}
	
	public static ContractService getInstance() {
		if (instance == null) {
			instance = new ContractService();
		}
		return instance;
	}
	
	
	
	
}