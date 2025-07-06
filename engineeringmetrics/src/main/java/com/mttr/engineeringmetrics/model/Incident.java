package com.mttr.engineeringmetrics.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceName;

	private LocalDateTime occurredAt;

	private LocalDateTime resolvedAt;

	private String severity;

	public Incident() {
	}

	public Incident(String serviceName, LocalDateTime occurredAt, LocalDateTime resolvedAt, String severity) {
		this.serviceName = serviceName;
		this.occurredAt = occurredAt;
		this.resolvedAt = resolvedAt;
		this.severity = severity;
	}

	// ✅ Add getters
	public Long getId() {
		return id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public LocalDateTime getOccurredAt() {
		return occurredAt;
	}

	public LocalDateTime getResolvedAt() {
		return resolvedAt;
	}

	public String getSeverity() {
		return severity;
	}

	// ✅ Optionally add setters if needed
}
