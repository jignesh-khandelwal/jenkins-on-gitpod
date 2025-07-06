package com.mttr.engineeringmetrics.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Deployment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceName;

	private LocalDateTime deployedAt;

	private String status; // SUCCESS / FAILED

	public Deployment(String serviceName, LocalDateTime deployedAt, String status) {
		this.serviceName = serviceName;
		this.deployedAt = deployedAt;
		this.status = status;
	}

	public boolean isFailed() {
		return this.status != null && this.status.equalsIgnoreCase("failed");
	}

	// Getters & Setters
	// ...generate via Eclipse or manually
}
