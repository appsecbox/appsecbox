package team.appsec.appsecbox.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.appsec.appsecbox.domain.assessment.Control;
import team.appsec.appsecbox.repository.ControlRepository;
import team.appsec.appsecbox.service.ControlOrchestratorService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ControlOrchestratorServiceImpl implements ControlOrchestratorService {

    private final ControlRepository controlRepository;

    @Override
    public Control getControlByName(String name) {
        return controlRepository.findByName(name).orElseThrow(ControlNotFoundException::new);
    }

    @Override
    public Control getControlById(UUID id) {
        return controlRepository.findById(id).orElseThrow(ControlNotFoundException::new);
    }

    public static class ControlNotFoundException extends RuntimeException{}
}
