package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	public Integer state;

	@Temporal(TemporalType.DATE)
	public LocalDate createdAt;

	@Temporal(TemporalType.DATE)
	public LocalDate updatedAt;
	
	@PrePersist
	public void prePersist() {
		this.state = 1;
		this.createdAt = LocalDate.now();
		this.updatedAt = LocalDate.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDate.now();
	}
}