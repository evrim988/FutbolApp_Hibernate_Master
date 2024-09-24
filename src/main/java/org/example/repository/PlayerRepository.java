package org.example.repository;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository extends RepositoryManager<Player, Integer> {

    public PlayerRepository() {
        super(Player.class);
    }

   /* public List<Player> findAllByTeamName(String teamName) {



        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Player> criteriaQuery = criteriaBuilder.createQuery(Player.class);
        Root<Player> root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("team"), teamId));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }*/
}
