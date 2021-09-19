package team.appsec.appsecbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.appsec.appsecbox.domain.assessment.Job;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID> {
}
