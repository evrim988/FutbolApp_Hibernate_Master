package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tblmanager")
public class Manager extends Person {
	
	private String managerUserName;
	private String managerPassword;

	@OneToOne
	@JoinColumn(name = "team_id", referencedColumnName = "id")
	private Team team;
	
	
	public Manager(String managerUserName, String managerPassword) {
		this.managerUserName = managerUserName;
		this.managerPassword = managerPassword;
		
	}
	
	public Manager(int i, String s, String s1, int i1, String s2, String s3, String s4) {
	}
}