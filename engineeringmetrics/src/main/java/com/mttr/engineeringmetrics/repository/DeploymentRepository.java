package com.mttr.engineeringmetrics.repository;

import com.mttr.engineeringmetrics.model.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeploymentRepository extends JpaRepository<Deployment, Long> {
	List<Deployment> findByDeployedAtBetween(LocalDateTime start, LocalDateTime end);
}
