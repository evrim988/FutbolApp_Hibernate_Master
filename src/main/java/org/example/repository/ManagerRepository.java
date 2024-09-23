package org.example.repository;

import org.example.entities.Manager;

public class ManagerRepository extends RepositoryManager<Manager, Integer> {

    public ManagerRepository() {
        super(Manager.class);
    }

}
