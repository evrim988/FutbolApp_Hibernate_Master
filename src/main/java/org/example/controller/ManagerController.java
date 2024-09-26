package org.example.controller;

import org.example.entities.Manager;
import org.example.service.ManagerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManagerController {

    private final ManagerService managerService;

    public ManagerController() {
        this.managerService = new ManagerService();
    }

    public Manager save(Manager manager) {
        try {
            return managerService.save(manager);
        } catch (Exception e) {
            System.out.println("Controller: Manager kaydetme sırasında hata oluştu..." + e.getMessage());
        }
        return null;
    }

    public Manager update(Manager manager) {
        try {
            return managerService.update(manager);
        }
        catch (Exception e) {
            System.out.println("Controller: Manager güncelleme sırasında hata oluştu..." + e.getMessage());
        }
        return null;
    }

    public Optional<Manager> findById(int id) {
        try {
            return managerService.findById(id);
        } catch (Exception e) {
            System.out.println("Controller: Manager bulma sırasında hata oluştu... " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Manager> findAll() {
        try {
            return managerService.findAll();
        } catch (Exception e) {
            System.out.println("Controller: Manager listeleme sırasında hata oluştu... " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<Manager> findAllByLeague(Integer id) {
        return managerService.findAllByLeague(id);
    }

    public Optional<Manager> findByUserNameAndPassword(String username, String password) {
        return managerService.findByUserNameAndPassword(username, password);
    }

    public Optional<Manager> findByTeamId(Integer teamId) {
        return managerService.findByTeamId(teamId);
    }

    public boolean existsByUsername(String username) {
        return managerService.existsByUsername(username);
    }
}
