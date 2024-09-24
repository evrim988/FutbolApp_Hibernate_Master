package org.example.repository;

import jakarta.persistence.Query;
import org.example.entities.League;
import org.example.entities.Manager;

import java.util.List;

public class ManagerRepository extends RepositoryManager<Manager, Integer> {

    public ManagerRepository() {
        super(Manager.class);
    }



}
