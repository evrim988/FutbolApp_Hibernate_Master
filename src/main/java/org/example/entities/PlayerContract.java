package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "tblplayercontract")
public class PlayerContract extends BaseEntity {

    Double wageOffer;
    LocalDate contractStartDate;
    LocalDate contractEndDate;

    @ManyToOne
    @JoinColumn(name = "teamid", referencedColumnName = "id")
    Team team;

    @ManyToOne
    @JoinColumn(name = "playerid", referencedColumnName = "id")
    Player player;

}
