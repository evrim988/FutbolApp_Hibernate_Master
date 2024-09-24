package org.example.repository;

import org.example.entities.GameDate;

public class GameDateRepository extends RepositoryManager<GameDate, Integer> {

    public GameDateRepository() {
        super(GameDate.class);
    }

}
