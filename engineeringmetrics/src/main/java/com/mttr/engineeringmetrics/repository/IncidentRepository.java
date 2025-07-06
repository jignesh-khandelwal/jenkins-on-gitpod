package com.mttr.engineeringmetrics.repository;

import com.mttr.engineeringmetrics.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
	List<Incident> findByOccurredAtBetween(LocalDateTime start, LocalDateTime end);
}
