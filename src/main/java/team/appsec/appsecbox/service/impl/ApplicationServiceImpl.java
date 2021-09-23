package team.appsec.appsecbox.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.appsec.appsecbox.domain.application.Application;
import team.appsec.appsecbox.domain.application.Architecture;
import team.appsec.appsecbox.domain.application.Component;
import team.appsec.appsecbox.domain.application.UseCase;
import team.appsec.appsecbox.repository.ApplicationRepository;
import team.appsec.appsecbox.repository.ArchitectureRepository;
import team.appsec.appsecbox.service.ApplicationService;
import team.appsec.appsecbox.service.ComponentService;
import team.appsec.appsecbox.service.UseCaseService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ArchitectureRepository architectureRepository;
    private final ComponentService componentService;
    private final UseCaseService useCaseService;

    @Transactional
    @Override
    public Application createNewApplication(String name) {
        Application application = applicationRepository.save(
                Application.builder()
                        .id(UUID.randomUUID())
                        .name(name)
                        .build());
        Architecture architecture = Architecture.builder()
                .id(UUID.randomUUID())
                .application(application)
                .build();
        architectureRepository.save(architecture);
        return getApplicationById(application.getId());
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
    public void addUseCaseToApplication(UUID applicationId, UUID useCaseId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(ApplicationNotFoundException::new);
        UseCase useCase = useCaseService.getById(useCaseId);
        Set<UseCase> useCases = application.getUseCases();
        if( useCase==null){
            useCases = new HashSet<>();
        }
        useCases.add(useCase);
        application.setUseCases(useCases);
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
