package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.enums.EOfferStatus;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblcontractoffer")
public class ContractOffer extends BaseEntity {

    Double wageOffer;
    LocalDate contractStartDate;
    LocalDate contractEndDate;
    @Enumerated(EnumType.STRING)
    EOfferStatus offerStatus;

    @ManyToOne
    @JoinColumn(name = "teamid", referencedColumnName = "id")
    Team team;

    @ManyToOne
    @JoinColumn(name = "playerid", referencedColumnName = "id")
    Player player;
    
    public String displayOffer(){
        StringBuilder str = new StringBuilder();
        str.append("Offer id: " + this.getId() + "\n");
        str.append("Team: " + this.team.getTeamName() + "\n");
        str.append("Player: " + this.player.getPersonName() + " " + this.player.getPersonSurname() + "\n");
        str.append("Wage Offer: " + this.wageOffer + "\n");
        str.append("Contract S. Date: " + this.contractStartDate + "\n");
        str.append("Contract E. Date: " + this.contractEndDate + "\n");
        str.append("Offer Status: " + this.offerStatus + "\n");
        return str.toString();
    }
}