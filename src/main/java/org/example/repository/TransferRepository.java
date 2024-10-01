package org.example.repository;

import org.example.entities.Team;
import org.example.entities.Transfer;

public class TransferRepository extends RepositoryManager<Transfer, Integer>{
	
	public TransferRepository() {
		super(Transfer.class);
	}
}