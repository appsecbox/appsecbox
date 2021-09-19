package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.assessment.Control;

import java.util.UUID;

public interface ControlOrchestratorService {
    Control getControlByName(String name);
    Control getControlById(UUID id);
}
