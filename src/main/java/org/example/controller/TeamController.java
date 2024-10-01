package org.example.controller;

import org.example.entities.Manager;
import org.example.entities.Player;
import org.example.entities.Team;
import org.example.service.TeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamController {

    private final TeamService teamService;

    public TeamController() {
        this.teamService = TeamService.getInstance();
    }

    public Team save(Team team) {
        return teamService.save(team);
    }
    
    public Team update(Team team) {
        return teamService.update(team);
    }
    
    public Optional<Team> findById(int id) {
        try {
            return teamService.findById(id);
        } catch (Exception e) {
            System.out.println("Controller: Team bulma sırasında hata oluştu... " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Team> findAll() {
        try {
            return teamService.findAll();
        }
        catch (Exception e) {
            System.out.println("Controller: Team listeleme sırasında hata oluştu... " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<Team> findByFieldNameAndValue(String name, Object value) {
        try {
            return teamService.findByFieldNameAndValue(name, value);
        }
        catch (Exception e) {
            System.out.println("Controller: Team bulmada sorun..." + e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<Team> findAllByLeague(Integer id) {
        return teamService.findAllByLeague(id);
    }

    public Optional<Team> findByTeamName(String teamName) {
        return teamService.findByTeamName(teamName);
    }

    public List<Team> findByFieldNameAndValueEqual(String fieldName, Object value) {
        return teamService.findByFieldNameAndValueEqual(fieldName, value);
    }

}