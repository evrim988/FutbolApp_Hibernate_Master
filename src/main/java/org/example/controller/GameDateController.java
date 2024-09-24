package org.example.controller;

import org.example.entities.GameDate;
import org.example.entities.Team;
import org.example.service.GameDateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameDateController {

    private final GameDateService gameDateService;

    public GameDateController() {
        this.gameDateService = GameDateService.getInstance();
    }

    public GameDate saveGameDate(GameDate gameDate) {
        try{
            return gameDateService.save(gameDate);
        }
        catch(Exception e){
            System.out.println("Controller: GameDate kaydetme sırasında hata oluştu..." + e.getMessage());
        }
        return null;
    }

    public Optional<GameDate> findById(int id) {
        try {
            return gameDateService.findById(id);
        } catch (Exception e) {
            System.out.println("Controller: Team bulma sırasında hata oluştu... " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<GameDate> findAll() {
        try {
            return gameDateService.findAll();
        }
        catch (Exception e) {
            System.out.println("Controller: Team listeleme sırasında hata oluştu... " + e.getMessage());
        }
        return new ArrayList<>();
    }

}
