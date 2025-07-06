package com.mttr.engineeringmetrics.service;

import com.mttr.engineeringmetrics.model.Deployment;
import com.mttr.engineeringmetrics.model.Incident;
import com.mttr.engineeringmetrics.repository.DeploymentRepository;
import com.mttr.engineeringmetrics.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class MetricService {

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private DeploymentRepository deploymentRepository;

    public MetricsResponse calculateMetrics() {
        List<Deployment> deployments = deploymentRepository.findAll();
        List<Incident> incidents = incidentRepository.findAll();

        int totalDeployments = deployments.size();
        int totalIncidents = incidents.size();

        long totalRecoveryMinutes = incidents.stream()
                .filter(i -> i.getResolvedAt() != null && i.getOccurredAt() != null)
                .mapToLong(i -> Duration.between(i.getOccurredAt(), i.getResolvedAt()).toMinutes())
                .sum();

        double mttr = totalIncidents == 0 ? 0 : (double) totalRecoveryMinutes / totalIncidents;
        long failedDeployments = deployments.stream().filter(Deployment::isFailed).count();
        double changeFailureRate = totalDeployments == 0 ? 0 : (double) failedDeployments / totalDeployments;

        return new MetricsResponse(changeFailureRate, mttr, totalDeployments, totalIncidents);
    }

    public static class MetricsResponse {
        public double changeFailureRate;
        public double meanTimeToRecoveryMinutes;
        public int totalDeployments;
        public int totalIncidents;

        public MetricsResponse(double changeFailureRate, double mttr, int deployments, int incidents) {
            this.changeFailureRate = changeFailureRate;
            this.meanTimeToRecoveryMinutes = mttr;
            this.totalDeployments = deployments;
            this.totalIncidents = incidents;
        }

        // Getters (for JSON serialization)
        public double getChangeFailureRate() {
            return changeFailureRate;
        }

        public double getMeanTimeToRecoveryMinutes() {
            return meanTimeToRecoveryMinutes;
        }

        public int getTotalDeployments() {
            return totalDeployments;
        }

        public int getTotalIncidents() {
            return totalIncidents;
        }
    }
}
