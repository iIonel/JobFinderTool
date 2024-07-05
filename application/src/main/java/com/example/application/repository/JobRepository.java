package com.example.application.repository;
import com.example.application.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByJobLink(String jobLink);
}
