package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Team;
import org.example.entities.TransferOffer;

import java.util.List;

public class TransferOfferRepository extends RepositoryManager<TransferOffer, Integer> {

    public TransferOfferRepository() {
        super(TransferOffer.class);
    }

    public List<TransferOffer> findByBuyerTeam(Team team) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TransferOffer> criteriaQuery = criteriaBuilder.createQuery(TransferOffer.class);
        Root<TransferOffer> from = criteriaQuery.from(TransferOffer.class);
        criteriaQuery.select(from).where(criteriaBuilder.equal(from.get("buyerClub"), team));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<TransferOffer> findByOwnerTeam(Team team) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TransferOffer> criteriaQuery = criteriaBuilder.createQuery(TransferOffer.class);
        Root<TransferOffer> from = criteriaQuery.from(TransferOffer.class);
        criteriaQuery.select(from).where(criteriaBuilder.equal(from.get("ownerClub"), team));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}
