package org.example.repository;

import org.example.entities.Player;

public class PlayerRepository extends RepositoryManager<Player, Integer> {

    public PlayerRepository() {
        super(Player.class);
    }
}
