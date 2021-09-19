package team.appsec.appsecbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.appsec.appsecbox.domain.assessment.Control;

import java.util.Optional;
import java.util.UUID;

public interface ControlRepository extends JpaRepository<Control, UUID> {
    Optional<Control> findByName(String name);
}
