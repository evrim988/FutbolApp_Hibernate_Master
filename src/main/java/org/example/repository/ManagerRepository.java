package org.example.repository;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.League;
import org.example.entities.Manager;

import java.util.List;

public class ManagerRepository extends RepositoryManager<Manager, Integer> {

    public ManagerRepository() {
        super(Manager.class);
    }

    public List<String> findAllManagerUsername(String username) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Manager> managerRoot = criteriaQuery.from(Manager.class);
        criteriaQuery.select(managerRoot.get("managerUserName")).where(criteriaBuilder.equal(managerRoot.get("managerUserName"), username));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

}
