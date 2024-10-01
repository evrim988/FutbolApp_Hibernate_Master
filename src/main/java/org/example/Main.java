package org.example;

import org.example.entities.Manager;
import org.example.entities.Team;
import org.example.enums.EPosition;
import org.example.models.DatabaseModels;
import org.example.modules.Menu;
import org.example.repository.RepositoryManager;
import org.example.service.ManagerService;
import org.example.service.ServiceManager;
import org.example.service.TeamService;
import org.example.utility.DemoData;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        DemoData dd = new DemoData();
        //dd.createDemoData();

        Menu.startMenu();


    }
}