package org.example.repository;

import org.example.entities.TeamStats;

public class TeamStatRepository extends RepositoryManager<TeamStats, Integer> {

    public TeamStatRepository() {
        super(TeamStats.class);
    }
}
