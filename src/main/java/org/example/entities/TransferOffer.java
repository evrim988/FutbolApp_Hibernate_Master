package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @JoinColumn(name = "biddingteamid", referencedColumnName = "id")
    Team biddingTeam; //teklif yapan

    @ManyToOne
    @JoinColumn(name = "receivingteamid", referencedColumnName = "id")
    Team receivingTeam; //teklif alan
}
