package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.application.TechnicalComponent;
import team.appsec.appsecbox.domain.assessment.Control;

import java.util.UUID;

public interface TechnicalComponentService {
    TechnicalComponent createTechnicalComponent(String uri, Control.Type type);
    TechnicalComponent getTechnicalComponentById(UUID technicalComponentId);
    void addMetaToTechnicalComponent(UUID technicalComponentId, String key, String value);
    void destroyTechnicalComponent(UUID technicalComponentId);
}
