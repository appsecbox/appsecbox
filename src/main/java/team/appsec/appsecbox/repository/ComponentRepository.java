package team.appsec.appsecbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.appsec.appsecbox.domain.application.Component;

import java.util.UUID;

public interface ComponentRepository extends JpaRepository<Component, UUID> {

}
