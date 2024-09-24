package org.example.service;

import org.example.entities.GameDate;
import org.example.repository.GameDateRepository;
import org.example.repository.ICRUD;

public class GameDateService extends ServiceManager<GameDate, Integer> {

    private final GameDateRepository gameDateRepository;

    public GameDateService() {
        this(new GameDateRepository());
    }

    private GameDateService(GameDateRepository gameDateRepository) {
        super(gameDateRepository);
        this.gameDateRepository = gameDateRepository;
    }


}
