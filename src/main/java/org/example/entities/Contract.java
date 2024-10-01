package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblcontract")
public class Contract extends BaseEntity {

    Double wage;
    LocalDate contractStartDate;
    LocalDate contractEndDate;

    @ManyToOne
    Team team;

    @ManyToOne
    Player player;

    @OneToOne(cascade = CascadeType.PERSIST)
    Transfer transfer;

    public String displayContract(){
        StringBuilder str = new StringBuilder();
        str.append("Contract id: " + this.getId() + "\n");
        str.append("Team: " + this.team.getTeamName() + "\n");
        str.append("Player: " + this.player.getPersonName() + " " + this.player.getPersonSurname() + "\n");
        str.append("Wage: " + this.wage + "\n");
        str.append("Start Date: " + this.contractStartDate);
        str.append("End Date: " + this.contractEndDate);
        return str.toString();
    }

}
