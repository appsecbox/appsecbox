package team.appsec.appsecbox.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.appsec.appsecbox.domain.application.Application;
import team.appsec.appsecbox.domain.application.Component;
import team.appsec.appsecbox.repository.ApplicationRepository;
import team.appsec.appsecbox.service.ApplicationService;
import team.appsec.appsecbox.service.ComponentService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ComponentService componentService;

    @Transactional
    @Override
    public Application createNewApplication(String name) {
        return applicationRepository.save(
                Application.builder()
                        .id(UUID.randomUUID())
                        .name(name)
                        .build());
    }

    @Override
    public Set<Application> getApplications() {
        return applicationRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public Application getApplicationById(UUID id) {
        return applicationRepository.findById(id).orElseThrow(ApplicationNotFoundException::new);
    }

    @Override
    public void addMetaToApplication(UUID applicationId, String key, String value) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(ApplicationNotFoundException::new);
        Map<String,String> meta = application.getMeta();
        meta.put(key,value);
        application.setMeta(meta);
        applicationRepository.save(application);
    }

    @Override
    public void addComponentToApplication(UUID applicationId, UUID componentId) {
        Application application = getApplicationById(applicationId);
        Component component = componentService.getComponentById(componentId);
        Set<Component> components = application.getComponents();
        if(components==null){
            components = new HashSet<>();
        }
        components.add(component);
        application.setComponents(components);
        applicationRepository.save(application);
    }

    @Override
    public void removeComponentFromApplication(UUID applicationId, UUID componentId) {
        Application application = getApplicationById(applicationId);
        Set<Component> components = application.getComponents();
        Set<Component> newComponents = components.stream().filter(c -> {
            return !c.getId().equals(componentId);
        }).collect(Collectors.toSet());
        application.setComponents(newComponents);
        applicationRepository.save(application);
    }

    @Override
    public void destroyApplication(UUID applicationId) {
        applicationRepository.deleteById(applicationId);
    }


    public static class ApplicationNotFoundException extends RuntimeException { }
}
