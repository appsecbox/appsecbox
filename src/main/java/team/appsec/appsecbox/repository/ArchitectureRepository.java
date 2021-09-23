package team.appsec.appsecbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.appsec.appsecbox.domain.application.Architecture;

import java.util.UUID;

public interface ArchitectureRepository extends JpaRepository<Architecture, UUID> {
}
