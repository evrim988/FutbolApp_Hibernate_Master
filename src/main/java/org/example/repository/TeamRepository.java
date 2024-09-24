package org.example.repository;


import org.example.entities.Team;

import java.util.Optional;

public class TeamRepository extends RepositoryManager<Team, Integer>{

    public TeamRepository() {
        super(Team.class);
    }

    public Optional<Team> findByTeamName(String teamName) {
        try {
            Team team = (Team) getEntityManager()
                    .createNativeQuery("SELECT * FROM tblteams WHERE teamname = '" + teamName + "'", Team.class)
                    .getSingleResult();
            return Optional.ofNullable(team);
        } catch (Exception e) {
            System.out.println("Repository: Takım Bulmada Sorun:  " + e.getMessage());
        }
        return Optional.empty();
    }
}
