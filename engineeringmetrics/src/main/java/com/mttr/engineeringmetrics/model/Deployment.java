package com.mttr.engineeringmetrics.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Deployment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceName;

	private LocalDateTime deployedAt;

	private String status; // SUCCESS / FAILED

	// Constructors
	public Deployment() {
	}

	public Deployment(String serviceName, LocalDateTime deployedAt, String status) {
		this.serviceName = serviceName;
		this.deployedAt = deployedAt;
		this.status = status;
	}

	// Getters & Setters
	// ...generate via Eclipse or manually
}
