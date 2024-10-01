package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbltransfer")
public class Transfer extends BaseEntity{

    Double transferPrice;
    LocalDate transferDate;

    @ManyToOne
    Player player;

    @ManyToOne
    Team buyerClub;

    @ManyToOne
    Team sellerClub;

    @OneToOne(cascade = CascadeType.PERSIST)
    Contract contract;

    public String displayTransfer(){
        StringBuilder str = new StringBuilder();
        str.append("Offer id: " + this.getId() + "\n");
        str.append("Bidding Team: " + this.buyerClub.getTeamName() + "\n");
        str.append("Owner Team: " + this.sellerClub.getTeamName() + "\n");
        str.append("Player: " + this.player.getPersonName() + " " + this.player.getPersonSurname() + "\n");
        str.append("Transfer Price: " + this.transferPrice + "\n");
        str.append("Transfer Date: " + this.transferDate);
        return str.toString();
    }

}
