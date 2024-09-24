package org.example.service;

import org.example.entities.GameDate;
import org.example.repository.GameDateRepository;
import org.example.repository.ICRUD;

public class GameDateService extends ServiceManager<GameDate, Integer> {

    private final GameDateRepository gameDateRepository;

    private static GameDateService instance;

    private GameDateService() {
        this(new GameDateRepository());
    }

    private GameDateService(GameDateRepository gameDateRepository) {
        super(gameDateRepository);
        this.gameDateRepository = gameDateRepository;
    }

    public static GameDateService getInstance() {
        if (instance == null) {
            instance = new GameDateService();
        }
        return instance;
    }


}
