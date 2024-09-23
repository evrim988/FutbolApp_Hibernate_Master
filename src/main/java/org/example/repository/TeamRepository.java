package org.example.repository;


import org.example.entities.Team;

public class TeamRepository extends RepositoryManager<Team, Integer>{

    public TeamRepository() {
        super(Team.class);
    }
}
