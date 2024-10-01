package org.example.controller;

import org.example.entities.Contract;
import org.example.entities.Team;
import org.example.entities.Transfer;
import org.example.service.ContractService;
import org.example.service.LeagueService;

import java.util.List;
import java.util.Optional;

public class ContractController {
	private final ContractService contractService;
	
	public ContractController() {
		this.contractService = ContractService.getInstance();
	}
	
	public Contract save(Contract contract) {
		return contractService.save(contract);
	}
	
	public Contract update(Contract contract) {
		return contractService.update(contract);
	}
	
	public Optional<Contract> findById(int id) {
		return contractService.findById(id);
	}

	public List<Contract> findAllByTeam(Team team) {
		return contractService.findAllByTeam(team);
	}
}