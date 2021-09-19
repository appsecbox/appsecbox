package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.application.Component;

import java.util.UUID;

public interface ComponentService {
    Component createComponent(String name);
    Component getComponentById(UUID componentId);
    void addTechnicalComponentToComponent(UUID componentId, UUID technicalComponentId);
    void removeTechnicalComponentFromComponent(UUID componentId, UUID technicalComponentId);
    void destroyComponent(UUID componentId);
}
