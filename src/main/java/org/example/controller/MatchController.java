package org.example.controller;

import org.example.entities.Match;
import org.example.service.MatchService;

import java.util.List;

public class MatchController {

    private final MatchService matchService;

    public MatchController() {
        this.matchService = new MatchService();
    }

    public List<Match> findAll() {
        return matchService.findAll();
    }

    public List<Match> findAllByTeamId(Integer teamId) {
        return matchService.findAllByTeamId(teamId);
    }

    public List<Match> findAllByLeagueId(Integer leagueId) {
        return matchService.findAllByLeagueId(leagueId);
    }
}
