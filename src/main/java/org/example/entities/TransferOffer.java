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
@Table(name = "tbltransferoffer")
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
        str.append("Offer id: " + this.getId() + "\n");
        str.append("Bidding Team: " + this.buyerClub.getTeamName() + "\n");
        str.append("Owner Team: " + this.ownerClub.getTeamName() + "\n");
        str.append("Player: " + this.player.getPersonName() + " " + this.player.getPersonSurname() + "\n");
        str.append("Offer Type: " + this.offerType + "\n");
        str.append("Offer Price: " + this.offerPrice + "\n");
        str.append("Offer Status: " + this.offerStatus + "\n");
        str.append("Offer Date: " + this.offerDate);
        return str.toString();
    }
}
