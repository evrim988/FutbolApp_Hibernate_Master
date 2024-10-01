package org.example.service;

import org.example.entities.Team;
import org.example.entities.Transfer;
import org.example.repository.LeagueRepository;
import org.example.repository.TransferRepository;

import java.util.List;

public class TransferService extends ServiceManager<Transfer, Integer>  {
	
	private final TransferRepository transferRepository;
	
	private static TransferService instance;
	
	private TransferService() {
		this(new TransferRepository());
	}
	
	private TransferService(TransferRepository repository) {
		super(repository);
		this.transferRepository = repository;
	}
	
	public static TransferService getInstance() {
		if (instance == null) {
			instance = new TransferService();
		}
		return instance;
	}

	public List<Transfer> findAllTopTransfers(){
		return transferRepository.findAllTopTransfers();
	}

	public List<Transfer> findAllTransferByTeam(Team team){
		return transferRepository.findAllTransferByTeam(team);
	}
}