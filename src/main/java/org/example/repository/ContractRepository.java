package org.example.repository;

import org.example.entities.Contract;
import org.example.entities.Transfer;

public class ContractRepository extends RepositoryManager<Contract, Integer>{
	
	public ContractRepository() {
		super(Contract.class);
	}
}