package team.appsec.appsecbox.service;

import team.appsec.appsecbox.domain.application.Application;
import team.appsec.appsecbox.domain.application.UseCase;

import java.util.Set;
import java.util.UUID;

public interface ApplicationService {
    Application createNewApplication(String name);
    Set<Application> getApplications();
    Application getApplicationById(UUID id);
    void addMetaToApplication(UUID applicationId, String key, String value);
    void addUseCaseToApplication(UUID applicationId, UUID useCaseId);
    void addComponentToApplication(UUID applicationId, UUID componentId);
    void removeComponentFromApplication(UUID applicationId, UUID componentId);
    void destroyApplication(UUID applicationId);
}
