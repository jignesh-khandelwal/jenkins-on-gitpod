package com.mttr.engineeringmetrics.bootstrap;

import com.mttr.engineeringmetrics.model.Deployment;
import com.mttr.engineeringmetrics.model.Incident;
import com.mttr.engineeringmetrics.repository.DeploymentRepository;
import com.mttr.engineeringmetrics.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SampleDataLoader implements ApplicationRunner {

    @Autowired
    private DeploymentRepository deploymentRepo;

    @Autowired
    private IncidentRepository incidentRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (deploymentRepo.count() == 0 && incidentRepo.count() == 0) {
            deploymentRepo.save(new Deployment("ServiceA", LocalDateTime.now().minusDays(5), "SUCCESS"));
            deploymentRepo.save(new Deployment("ServiceB", LocalDateTime.now().minusDays(3), "FAILED"));
            deploymentRepo.save(new Deployment("ServiceC", LocalDateTime.now().minusDays(2), "SUCCESS"));

            incidentRepo
                    .save(new Incident("ServiceB", LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(2),
                            "CRITICAL"));
            incidentRepo.save(new Incident("ServiceA", LocalDateTime.now().minusDays(1), LocalDateTime.now(), "HIGH"));

            // Deployment d1 = new Deployment(null, "Success",
            // LocalDateTime.now().minusDays(5));
            // Deployment d2 = new Deployment(null, "Fail",
            // LocalDateTime.now().minusDays(4));
            // Deployment d3 = new Deployment(null, "Success",
            // LocalDateTime.now().minusDays(3));
            // deploymentRepo.save(d1);
            // deploymentRepo.save(d2);
            // deploymentRepo.save(d3);

            // Incident i1 = new Incident(null, "Login issue",
            // LocalDateTime.now().minusDays(4),
            // LocalDateTime.now().minusDays(4).plusHours(2));
            // Incident i2 = new Incident(null, "Deployment bug",
            // LocalDateTime.now().minusDays(3),
            // LocalDateTime.now().minusDays(3).plusHours(1));
            // incidentRepo.save(i1);
            // incidentRepo.save(i2);

            System.out.println("✅ Sample data inserted on startup.");
        }
    }
}
