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

}
