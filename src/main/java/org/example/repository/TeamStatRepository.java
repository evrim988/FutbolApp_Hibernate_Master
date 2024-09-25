package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Team;
import org.example.entities.TeamStats;

import java.util.Optional;

public class TeamStatRepository extends RepositoryManager<TeamStats, Integer> {

    public TeamStatRepository() {
        super(TeamStats.class);
    }

    public Optional<TeamStats> findByTeamId(Team team) {
        try {
            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TeamStats> criteriaQuery = criteriaBuilder.createQuery(TeamStats.class);
            Root<TeamStats> teamStatsRoot = criteriaQuery.from(TeamStats.class);
            criteriaQuery.select(teamStatsRoot).where(criteriaBuilder.equal(teamStatsRoot.get("team"), team));
            TeamStats teamStats = getEntityManager().createQuery(criteriaQuery).getSingleResult();
            return Optional.ofNullable(teamStats);

        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
}
