package org.example.controller;

import org.example.entities.Manager;
import org.example.entities.Player;
import org.example.repository.PlayerRepository;
import org.example.service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerController {

    private final PlayerService playerService;

    public PlayerController() {
        this.playerService = PlayerService.getInstance();
    }

    public Optional<Player> findById(int id) {
        try {
            return playerService.findById(id);
        } catch (Exception e) {
            System.out.println("Controller: Manager bulma sırasında hata oluştu... " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Player> findAll() {
        try {
            return playerService.findAll();
        }
        catch (Exception e) {
            System.out.println("Controller: Manager listeleme sırasında hata oluştu... " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<Player> findByFieldNameAndValue(String fieldName, Object value) {
        return playerService.findByFieldNameAndValue(fieldName, value);
    }

   /* public List<Player> findAllByTeamName(String teamName) {
        return playerService.findAllByTeamName(teamName);
    }*/

    public List<Player> findAllByTeam(Integer id) {
        return playerService.findAllByTeam(id);
    }
}
