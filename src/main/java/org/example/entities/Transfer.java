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

}
