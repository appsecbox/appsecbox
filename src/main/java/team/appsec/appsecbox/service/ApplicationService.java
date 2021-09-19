package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.application.Application;

import java.util.UUID;

public interface ApplicationService {
    Application createNewApplication(String name);
    Application getApplicationById(UUID id);
    void addComponentToApplication(UUID applicationId, UUID componentId);
    void removeComponentFromApplication(UUID applicationId, UUID componentId);
    void destroyApplication(UUID applicationId);
}
