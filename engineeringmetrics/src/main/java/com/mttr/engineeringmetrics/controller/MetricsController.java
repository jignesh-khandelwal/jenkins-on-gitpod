package com.mttr.engineeringmetrics.controller;

import com.mttr.engineeringmetrics.model.*;
import com.mttr.engineeringmetrics.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MetricsController {

	@Autowired
	private DeploymentRepository deploymentRepo;

	@Autowired
	private IncidentRepository incidentRepo;

	// Sample data for testing
	@PostMapping("/insert-sample-data")
	public String insertSampleData() {
		deploymentRepo.save(new Deployment("ServiceA", LocalDateTime.now().minusDays(5), "SUCCESS"));
		deploymentRepo.save(new Deployment("ServiceB", LocalDateTime.now().minusDays(3), "FAILED"));
		deploymentRepo.save(new Deployment("ServiceC", LocalDateTime.now().minusDays(2), "SUCCESS"));

		incidentRepo.save(new Incident("ServiceB", LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(2),
				"CRITICAL"));
		incidentRepo.save(new Incident("ServiceA", LocalDateTime.now().minusDays(1), LocalDateTime.now(), "HIGH"));

		return "Sample data inserted";
	}

	// Calculate CFR & MTTR
	@GetMapping("/metrics")
	public Map<String, Object> calculateMetrics(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
		LocalDateTime from = start.atStartOfDay();
		LocalDateTime to = end.atTime(23, 59, 59);

		List<Deployment> deployments = deploymentRepo.findByDeployedAtBetween(from, to);
		List<Incident> incidents = incidentRepo.findByOccurredAtBetween(from, to);

		long deploymentCount = deployments.size();
		long incidentCount = incidents.size();

		double cfr = (deploymentCount == 0) ? 0.0 : (double) incidentCount / deploymentCount;

		long totalRecoveryMinutes = incidents.stream().filter(i -> i.getResolvedAt() != null)
				.mapToLong(i -> Duration.between(i.getOccurredAt(), i.getResolvedAt()).toMinutes()).sum();

		double mttr = (incidentCount == 0) ? 0.0 : (double) totalRecoveryMinutes / incidentCount;

		return Map.of("changeFailureRate", String.format("%.2f", cfr), "meanTimeToRecoveryMinutes",
				String.format("%.2f", mttr), "totalDeployments", deploymentCount, "totalIncidents", incidentCount);
	}

	@GetMapping("/api/incidents")
	public List<Incident> getAllIncidents() {
		return incidentRepo.findAll();
	}

	@GetMapping("/api/deployments")
	public List<Deployment> getAllDeployments() {
		return deploymentRepo.findAll();
	}
}
