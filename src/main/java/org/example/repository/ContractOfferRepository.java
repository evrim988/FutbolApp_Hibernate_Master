package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.ContractOffer;
import org.example.entities.Team;
import org.example.entities.TransferOffer;
import org.example.enums.EOfferStatus;

import java.util.List;

public class ContractOfferRepository extends RepositoryManager<ContractOffer, Integer> {

    public ContractOfferRepository() {
        super(ContractOffer.class);
    }
    
    public List<ContractOffer> findAcceptedByTeamId(Integer teamId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ContractOffer> criteriaQuery = criteriaBuilder.createQuery(ContractOffer.class);
        Root<ContractOffer> from = criteriaQuery.from(ContractOffer.class);
        
        criteriaQuery.select(from).where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(from.get("team").get("id"), teamId),
                        criteriaBuilder.equal(from.get("offerStatus"), EOfferStatus.ACCEPTED)
                )
        );
        
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}