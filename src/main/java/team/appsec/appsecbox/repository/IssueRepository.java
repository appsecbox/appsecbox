package team.appsec.appsecbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.appsec.appsecbox.domain.assessment.Issue;

import java.util.UUID;

public interface IssueRepository extends JpaRepository<Issue, UUID> {
}
