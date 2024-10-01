package org.example.repository;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Team;
import org.example.entities.Transfer;

import java.util.ArrayList;
import java.util.List;

public class TransferRepository extends RepositoryManager<Transfer, Integer>{
	
	public TransferRepository() {
		super(Transfer.class);
	}

	public List<Transfer> findAllTopTransfers(){
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Transfer> cq = cb.createQuery(Transfer.class);
			Root<Transfer> root = cq.from(Transfer.class);
			cq.select(root).orderBy(cb.desc(root.get("transferPrice")));
			TypedQuery<Transfer> query = getEntityManager().createQuery(cq);
			query.setMaxResults(10);
			return query.getResultList();

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	public List<Transfer> findAllTransferByTeam(Team team){
		try {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Transfer> cq = cb.createQuery(Transfer.class);
			Root<Transfer> root = cq.from(Transfer.class);
			cq.select(root).where(cb.equal(root.get("buyerClub"), team));
			return getEntityManager().createQuery(cq).getResultList();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}
}