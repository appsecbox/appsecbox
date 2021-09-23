package team.appsec.appsecbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.appsec.appsecbox.domain.application.Dataset;

import java.util.UUID;

public interface DatasetRepository extends JpaRepository<Dataset, UUID> {
}
