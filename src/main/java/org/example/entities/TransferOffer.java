package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.enums.EOfferStatus;
import org.example.enums.EOfferType;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbltransfer")
public class TransferOffer extends BaseEntity {

    Double offerPrice;
    LocalDate offerDate;

    @Enumerated(value = EnumType.STRING)
    EOfferType offerType;
    @Enumerated(value = EnumType.STRING)
    EOfferStatus offerStatus;

    @ManyToOne
    @JoinColumn(name = "playerid", referencedColumnName = "id")
    Player player;

    @ManyToOne
    @JoinColumn(name = "buyerteamid", referencedColumnName = "id")
    Team buyerClub; //teklif yapan -> buyerClub

    @ManyToOne
    @JoinColumn(name = "ownerteamid", referencedColumnName = "id")
    Team ownerClub; //teklif alan -> ownerClub

    public String displayOffer(){
        StringBuilder str = new StringBuilder();
        str.append("Bidding Team: " + this.buyerClub.getTeamName());
        str.append("Owner Team: " + this.ownerClub.getTeamName());
        str.append("Player: " + this.player.getPersonName() + " " + this.player.getPersonSurname());
        str.append("Offer Type: " + this.offerType);
        str.append("Offer Price: " + this.offerPrice);
        str.append("Offer Status: " + this.offerStatus);
        str.append("Offer Date: " + this.offerDate);
        return str.toString();
    }
}
