package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Contract;
import org.example.entities.Team;
import org.example.entities.Transfer;

import java.util.ArrayList;
import java.util.List;

public class ContractRepository extends RepositoryManager<Contract, Integer>{
	
	public ContractRepository() {
		super(Contract.class);
	}

	public List<Contract> findAllByTeam(Team team) {
		try{
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Contract> cq = cb.createQuery(Contract.class);
			Root<Contract> contract = cq.from(Contract.class);
			cq.select(contract).where(cb.equal(contract.get("team"), team));
			return getEntityManager().createQuery(cq).getResultList();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}
}