package team.appsec.appsecbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.appsec.appsecbox.domain.application.Application;

import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
}
