package org.example.utility;

import org.example.entities.League;
import org.example.entities.Manager;
import org.example.entities.Team;
import org.example.entities.attributes.TechnicalAttributes;
import org.example.enums.ERegion;
import org.example.repository.LeagueRepository;
import org.example.repository.ManagerRepository;
import org.example.repository.TeamRepository;

import java.time.LocalDate;

public class DemoData {

    LeagueRepository leagueRepository;
    TeamRepository teamRepository;
    ManagerRepository managerRepository;
    private League league;

    public DemoData() {
        this.leagueRepository = new LeagueRepository();
        this.teamRepository = new TeamRepository();
        this.managerRepository = new ManagerRepository();
    }

    public void createDemoData() {
        createLeague();
        createTeamsAndManagers();
    }

    private void createLeague() {
        league = League.builder()
                .leagueName("Trendyol Super Lig")
                .season("2024-2025")
                .division(1)
                .region(ERegion.TURKISH_LEAGUE)
                .seasonStartDate(LocalDate.parse("2024-08-23"))
                .seasonEndDate(LocalDate.parse("2025-06-01"))
                .build();
        leagueRepository.save(league);

    }

    private void createTeamsAndManagers() {
        Team team1 = Team.builder()
                .league(league)
                .teamName("Galatasaray")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Rams Park")
                .teamLocation("Istanbul")
                .build();

        Manager manager1 = Manager.builder()
                .personName("Okan")
                .personSurname("Buruk")
                .personAge(50)
                .personNationality("Turkiye")
                .managerUserName("okan")
                .managerPassword("1234")
                .team(team1)
                .build();
        teamRepository.save(team1);
        managerRepository.save(manager1);

        Team team2 = Team.builder()
                .league(league)
                .teamName("Fenerbahce")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Sukru Saracoglu Stadyumu")
                .teamLocation("Istanbul")
                .build();

        Manager manager2 = Manager.builder()
                .personName("Jose")
                .personSurname("Mourinho")
                .personAge(61)
                .personNationality("Portugal")
                .managerUserName("jose")
                .managerPassword("1234")
                .team(team2)
                .build();

        teamRepository.save(team2);
        managerRepository.save(manager2);

        Team team3 = Team.builder()
                .league(league)
                .teamName("Besiktas")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Tupras Stadyumu")
                .teamLocation("Istanbul")
                .build();

        Manager manager3 = Manager.builder()
                .personName("Giovanni van")
                .personSurname("Bronckhorst")
                .personAge(49)
                .personNationality("Netherlands")
                .managerUserName("giovanni")
                .managerPassword("1234")
                .team(team3)
                .build();

        teamRepository.save(team3);
        managerRepository.save(manager3);

        Team team4 = Team.builder()
                .league(league)
                .teamName("Trabzonspor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Avni Aker Stadyumu")
                .teamLocation("Trabzon")
                .build();

        Manager manager4 = Manager.builder()
                .personName("Abdullah")
                .personSurname("Avci")
                .personAge(61)
                .personNationality("Turkiye")
                .managerUserName("abdullah")
                .managerPassword("1234")
                .team(team4)
                .build();

        teamRepository.save(team4);
        managerRepository.save(manager4);

        Team team5 = Team.builder()
                .league(league)
                .teamName("Basaksehir")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Fatih Terim Stadyumu")
                .teamLocation("Istanbul")
                .build();

        Manager manager5 = Manager.builder()
                .personName("Cagdas")
                .personSurname("Atan")
                .personAge(44)
                .personNationality("Turkiye")
                .managerUserName("cagdas")
                .managerPassword("6783")
                .team(team5)
                .build();

        teamRepository.save(team5);
        managerRepository.save(manager5);


        Team team6 = Team.builder()
                .league(league)
                .teamName("Caykur Rizespor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Caykur Stadyumu")
                .teamLocation("Rize")
                .build();

        Manager manager6 = Manager.builder()
                .personName("İlhan")
                .personSurname("Palut")
                .personAge(47)
                .personNationality("Turkiye")
                .managerUserName("ilhan")
                .managerPassword("5214")
                .team(team6)
                .build();

        teamRepository.save(team6);
        managerRepository.save(manager6);

        Team team7 = Team.builder()
                .league(league)
                .teamName("Samsunspor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("19 Mayis Stadyumu")
                .teamLocation("Samsun")
                .build();

        Manager manager7 = Manager.builder()
                .personName("Thomas")
                .personSurname("Reis")
                .personAge(50)
                .personNationality("Germany")
                .managerUserName("thomas")
                .managerPassword("4325")
                .team(team7)
                .build();

        teamRepository.save(team7);
        managerRepository.save(manager7);

        Team team8 = Team.builder()
                .league(league)
                .teamName("Kayserispor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Kadir Has Stadyumu")
                .teamLocation("Kayseri")
                .build();

        Manager manager8 = Manager.builder()
                .personName("Burak")
                .personSurname("Yılmaz")
                .personAge(39)
                .personNationality("Turkiye")
                .managerUserName("burak")
                .managerPassword("3675")
                .team(team8)
                .build();

        teamRepository.save(team8);
        managerRepository.save(manager8);

        Team team9 = Team.builder()
                .league(league)
                .teamName("Eyupspor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Arda Turan Stadyumu")
                .teamLocation("Istanbul")
                .build();

        Manager manager9 = Manager.builder()
                .personName("Arda")
                .personSurname("Turan")
                .personAge(37)
                .personNationality("Turkiye")
                .managerUserName("Arda")
                .managerPassword("9999")
                .team(team9)
                .build();

        teamRepository.save(team9);
        managerRepository.save(manager9);

        Team team10 = Team.builder()
                .league(league)
                .teamName("Antalyaspor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Antalya Stadi")
                .teamLocation("Antalya")
                .build();

        Manager manager10 = Manager.builder()
                .personName("Alex")
                .personSurname("DeSouza")
                .personAge(46)
                .personNationality("Brazil")
                .managerUserName("Alex")
                .managerPassword("9876")
                .team(team10)
                .build();

        teamRepository.save(team10);
        managerRepository.save(manager10);

        Team team11 = Team.builder()
                .league(league)
                .teamName("Adana Demirspor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Yeni Adana Stadyumu")
                .teamLocation("Adana")
                .build();

        Manager manager11 = Manager.builder()
                .personName("Michael")
                .personSurname("Valkanis")
                .personAge(40)
                .personNationality("Avustralia")
                .managerUserName("Michi")
                .managerPassword("5432")
                .team(team11)
                .build();

        teamRepository.save(team11);
        managerRepository.save(manager11);

        Team team12 = Team.builder()
                .league(league)
                .teamName("Alanyaspor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Oba Stadyumu")
                .teamLocation("Antalya")
                .build();

        Manager manager12 = Manager.builder()
                .personName("Fatih")
                .personSurname("Tekke")
                .personAge(46)
                .personNationality("Turkiye")
                .managerUserName("Fatih")
                .managerPassword("2468")
                .team(team12)
                .build();

        teamRepository.save(team12);
        managerRepository.save(manager12);

        Team team13 = Team.builder()
                .league(league)
                .teamName("Sivasspor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("4 Eylul Stadi")
                .teamLocation("Sivas")
                .build();


        Manager manager13 = Manager.builder()
                .personName("Bülent")
                .personSurname("Uygun")
                .personAge(53)
                .personNationality("Turkiye")
                .managerUserName("bulent")
                .managerPassword("1234")
                .team(team13)
                .build();

        teamRepository.save(team13);
        managerRepository.save(manager13);

        Team team14 = Team.builder()
                .league(league)
                .teamName("Kasimpasa")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Recep Tayyip Erdogan Stadyumu")
                .teamLocation("Istanbul")
                .build();

        Manager manager14 = Manager.builder()
                .personName("Sami")
                .personSurname("Ugurlu")
                .personAge(46)
                .personNationality("Turkiye")
                .managerUserName("sami")
                .managerPassword("1234")
                .team(team14)
                .build();

        teamRepository.save(team14);
        managerRepository.save(manager14);

        Team team15 = Team.builder()
                .league(league)
                .teamName("Konyaspor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Konya Buyuksehir Stadyumu")
                .teamLocation("Konya")
                .build();

        Manager manager15 = Manager.builder()
                .personName("Ali")
                .personSurname("Camdali")
                .personAge(40)
                .personNationality("Turkiye")
                .managerUserName("ali")
                .managerPassword("1234")
                .team(team15)
                .build();

        teamRepository.save(team15);
        managerRepository.save(manager15);

        Team team16 = Team.builder()
                .league(league)
                .teamName("Gaziantep FK")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Kalyon Stadyumu")
                .teamLocation("Gaziantep")
                .build();

        Manager manager16 = Manager.builder()
                .personName("Selcuk")
                .personSurname("Inan")
                .personAge(39)
                .personNationality("Turkiye")
                .managerUserName("selcuk")
                .managerPassword("1234")
                .team(team16)
                .build();

        teamRepository.save(team16);
        managerRepository.save(manager16);

        Team team17 = Team.builder()
                .league(league)
                .teamName("Hatayspor")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Mersin Stadyumu")
                .teamLocation("Hatay")
                .build();

        Manager manager17 = Manager.builder()
                .personName("Ozhan")
                .personSurname("Pulat")
                .personAge(39)
                .personNationality("Turkiye")
                .managerUserName("ozhan")
                .managerPassword("1234")
                .team(team17)
                .build();

        teamRepository.save(team17);
        managerRepository.save(manager17);

        Team team18 = Team.builder()
                .league(league)
                .teamName("Goztepe")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Gursel Aksel Stadyumu")
                .teamLocation("Izmir")
                .build();

        Manager manager18 = Manager.builder()
                .personName("Stanimir")
                .personSurname("Stoilov")
                .personAge(57)
                .personNationality("Bulgaria")
                .managerUserName("stanimir")
                .managerPassword("1234")
                .team(team18)
                .build();

        teamRepository.save(team18);
        managerRepository.save(manager18);

        Team team19 = Team.builder()
                .league(league)
                .teamName("Bodrum FK")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("Bodrum Ilce Stadi")
                .teamLocation("Mugla")
                .build();

        Manager manager19 = Manager.builder()
                .personName("Ismet")
                .personSurname("Tasdemir")
                .personAge(50)
                .personNationality("Turkiye")
                .managerUserName("ismet")
                .managerPassword("1234")
                .team(team19)
                .build();

        teamRepository.save(team19);
        managerRepository.save(manager19);

        Team team20 = Team.builder()
                .league(league)
                .teamName("BYE")
                .transferBudget(1D)
                .wageBudget(1D)
                .stadiumName("0")
                .teamLocation("0")
                .build();

        teamRepository.save(team20);
    }

    public void createAttributes() {
        TechnicalAttributes ta = TechnicalAttributes.builder()
                .finishing(10)
                .pass(10)
                .dribbling(10)
                .tackle(10)
                .shotPower(10)
                .crossing(10)
                .header(10)
                .positioning(10)
                .firstTouch(10)
                .build();
    }


}
