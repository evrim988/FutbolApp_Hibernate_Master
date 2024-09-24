package org.example.controller;

import org.example.entities.League;
import org.example.entities.Manager;
import org.example.entities.Team;
import org.example.service.LeagueService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController() {
        this.leagueService = LeagueService.getInstance();
    }

    public Optional<League> findById(int id) {
        try {
            return leagueService.findById(id);
        } catch (Exception e) {
            System.out.println("Controller: Manager bulma sırasında hata oluştu... " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<League> findAll() {
        try {
            return leagueService.findAll();
        }
        catch (Exception e) {
            System.out.println("Controller: Manager listeleme sırasında hata oluştu... " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<League> findByFieldNameAndValue(String name, Object value) {
        try {
            return leagueService.findByFieldNameAndValue(name, value);
        }
        catch (Exception e) {
            System.out.println("Controller: League bulmada sorun..." + e.getMessage());
        }
        return new ArrayList<>();
    }


}
