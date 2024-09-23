package org.example.repository;

import org.example.entities.Match;

public class MatchRepository extends RepositoryManager<Match, Integer> {

    public MatchRepository() {
        super(Match.class);
    }
}
