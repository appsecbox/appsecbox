package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.application.Component;

import java.util.Set;
import java.util.UUID;

public interface ComponentService {
    Component createComponent(String name);
    Component getComponentById(UUID componentId);
    Set<Component> getComponentsByIds(Set<UUID> componentsIds);
    void addTechnicalComponentToComponent(UUID componentId, UUID technicalComponentId);
    void addDatasetToComponent(UUID componentId, UUID datasetId);
    void addMetaToComponent(UUID componentId, String key, String value);
    void removeTechnicalComponentFromComponent(UUID componentId, UUID technicalComponentId);
    void destroyComponent(UUID componentId);
}
