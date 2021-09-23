package team.appsec.appsecbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.appsec.appsecbox.domain.application.UseCase;

import java.util.UUID;

public interface UseCaseRepository extends JpaRepository<UseCase, UUID> {
}
