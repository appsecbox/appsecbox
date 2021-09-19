package team.appsec.appsecbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.appsec.appsecbox.domain.application.TechnicalComponent;

import java.util.UUID;

public interface TechnicalComponentRepository extends JpaRepository<TechnicalComponent, UUID> {
}
