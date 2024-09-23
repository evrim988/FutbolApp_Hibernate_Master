package org.example.repository;

import org.example.entities.League;
import org.example.entities.TeamStats;

public class LeagueRepository extends RepositoryManager<League, Integer> {

    public LeagueRepository() {
        super(League.class);
    }


}
