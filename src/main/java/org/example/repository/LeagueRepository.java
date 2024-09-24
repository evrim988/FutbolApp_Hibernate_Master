package org.example.repository;

import org.example.entities.League;
import org.example.entities.Team;
import org.example.entities.TeamStats;

import java.util.List;
import java.util.Optional;

public class LeagueRepository extends RepositoryManager<League, Integer> {

    public LeagueRepository() {
        super(League.class);
    }


}
