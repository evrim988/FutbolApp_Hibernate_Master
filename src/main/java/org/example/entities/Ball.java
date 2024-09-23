package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Ball {
	private Integer position=0; // Position on the field, 0 to 100
	private Player playerWithBall=null;
	private Boolean reference; // True always home team
}